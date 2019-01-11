package topic1.concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(0, 0);

    public static void main(String[] args) throws InterruptedException {

        final int stamp = atomicStampedReference.getStamp();
        final Integer reference = atomicStampedReference.getReference();

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());

        System.out.println("first " + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));

        System.out.println("second " + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }
}
