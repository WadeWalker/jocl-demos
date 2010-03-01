/*
 * 20:48 Sunday, February 28 2010
 */

package com.mbien.opencl.demos.radixsort;

import com.mbien.opencl.CLBuffer;
import com.mbien.opencl.CLCommandQueue;
import com.mbien.opencl.CLContext;
import com.mbien.opencl.CLPlatform;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Random;

import static com.mbien.opencl.CLMemory.Mem.*;
import static java.lang.System.*;
import static com.mbien.opencl.CLDevice.Type.*;

/**
 * GPU radix sort demo.
 * @author Michael Bien
 */
public class RadixSortDemo {

    public RadixSortDemo() throws IOException {

        CLContext context = null;
        try{
            //single GPU setup
            context = CLContext.create(CLPlatform.getDefault().getMaxFlopsDevice(GPU));
            CLCommandQueue queue = context.getDevices()[0].createCommandQueue();

            int maxValue = 10000000;

            int[] workgroupSizes = new int[] {128, 256};

            int[] runs = new int[] {  131072,
                                      262144,
                                      524288,
                                     1048576,
                                     2097152,
                                     4194304,
                                     8388608 };

            for (int i = 0; i < workgroupSizes.length; i++) {

                int workgroupSize = workgroupSizes[i];

                out.println("\n = = = workgroup size: "+workgroupSize+" = = = ");

                for(int run = 0; run < runs.length; run++) {

                    if(workgroupSize==128 && run == runs.length-1) {
                        break; // we can only sort up to 4MB with wg size of 128
                    }

                    int numElements = runs[run];

                    CLBuffer<IntBuffer> array = context.createIntBuffer(numElements, READ_WRITE);
                    out.print("array size: " + array.getCLSize()/1000000.0f+"MB; ");
                    out.println("elements: " + array.getCapacity()/1000+"K");

                    fillBuffer(array, maxValue);

                    RadixSort radixSort = new RadixSort(queue, numElements, workgroupSize);
                    queue.finish();

                    long time = currentTimeMillis();

                        queue.putWriteBuffer(array, false);
                        radixSort.sort(array, numElements, 32);
                        queue.putReadBuffer(array, true);

                    out.println("time: " + (currentTimeMillis() - time)+"ms");

                    out.print("snapshot: ");
                    printSnapshot(array.getBuffer(), 20);

                    out.println("validating...");
                    checkIfSorted(array.getBuffer());
                    out.println("values sorted");

                    array.release();
                    radixSort.release();
                }
            }
            
        }finally{
            if(context != null) {
                context.release();
            }
        }

    }

    private void fillBuffer(CLBuffer<IntBuffer> array, int maxValue) {
        Random random = new Random(42);
        for (int n = 0; n < array.getBuffer().capacity(); n++) {
            int rnd = random.nextInt(maxValue);
            array.getBuffer().put(n, rnd);
        }
    }

    private void printSnapshot(IntBuffer buffer, int snapshot) {
        for(int i = 0; i < snapshot; i++)
            out.print(buffer.get() + ", ");
        out.println("...; " + buffer.remaining() + " more");
        buffer.rewind();
    }

    private void checkIfSorted(IntBuffer keys) {
        for (int i = 1; i < keys.capacity(); i++) {
            if (keys.get(i - 1) > keys.get(i)) {
                throw new RuntimeException("not sorted "+ keys.get(i - 1) +" !> "+ keys.get(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new RadixSortDemo();
    }
}
