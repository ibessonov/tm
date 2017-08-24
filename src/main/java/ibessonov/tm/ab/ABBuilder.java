package ibessonov.tm.ab;

abstract class ABBuilder<X> {

    public static void main(String[] args) {
        E<? super E<? super String>> e = start.A().B().A().B().stop();
    }

    static ABBuilder<ML<? super N<? super L$<? super N<? super E<? super E<? super String>>>>>>> start;
    abstract QWRStart<? super L$<? super N<? super X>>> stop();

    abstract ABBuilder<LA<? super N<? super X>>> A();
    abstract ABBuilder<LB<? super N<? super X>>> B();

    public interface N<X> {}
    public interface ML<X> {}
    public interface MR<X> {}

    public interface LA<X> {}
    public interface LB<X> {}
    public interface Lx<X> {}
    public interface L$<X> {}

    public interface E<X> extends
            QLRStart<N<? super QWRStart<? super E<? super E<? super X>>>>>,
            QRLStart<N<? super QWLStart<? super E<? super E<? super X>>>>>,
            QLRScan<N<? super QWRScan<? super E<? super E<? super X>>>>>,
            QRLScan<N<? super QWLScan<? super E<? super E<? super X>>>>>,
            QLRBack<N<? super QWRBack<? super E<? super E<? super X>>>>>,
            QRLBack<N<? super QWLBack<? super E<? super E<? super X>>>>>,
            QLRCheck<N<? super QWRCheck<? super E<? super E<? super X>>>>>,
            QRLCheck<N<? super QWLCheck<? super E<? super E<? super X>>>>>,
            QLRHalt<N<? super QWRHalt<? super E<? super E<? super X>>>>>,
            QRLHalt<N<? super QWLHalt<? super E<? super E<? super X>>>>> {}

    public interface QLRStart<X> {}
    public interface QWLStart<X> extends
            ML<N<? super QLStart<? super X>>>,
            MR<N<? super QWLStart<? super MR<? super N<? super X>>>>>,
            LA<N<? super QWLStart<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWLStart<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWLStart<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWLStart<? super L$<? super N<? super X>>>>>,
            E<QLRStart<? super N<? super X>>> {}

    public interface QRLStart<X> {}
    public interface QWRStart<X> extends
            MR<N<? super QRStart<? super X>>>,
            ML<N<? super QWRStart<? super ML<? super N<? super X>>>>>,
            LA<N<? super QWRStart<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWRStart<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWRStart<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWRStart<? super L$<? super N<? super X>>>>>,
            E<QRLStart<? super N<? super X>>> {}

    public interface QLRScan<X> {}
    public interface QWLScan<X> extends
            ML<N<? super QLScan<? super X>>>,
            MR<N<? super QWLScan<? super MR<? super N<? super X>>>>>,
            LA<N<? super QWLScan<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWLScan<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWLScan<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWLScan<? super L$<? super N<? super X>>>>>,
            E<QLRScan<? super N<? super X>>> {}

    public interface QRLScan<X> {}
    public interface QWRScan<X> extends
            MR<N<? super QRScan<? super X>>>,
            ML<N<? super QWRScan<? super ML<? super N<? super X>>>>>,
            LA<N<? super QWRScan<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWRScan<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWRScan<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWRScan<? super L$<? super N<? super X>>>>>,
            E<QRLScan<? super N<? super X>>> {}

    public interface QLRBack<X> {}
    public interface QWLBack<X> extends
            ML<N<? super QLBack<? super X>>>,
            MR<N<? super QWLBack<? super MR<? super N<? super X>>>>>,
            LA<N<? super QWLBack<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWLBack<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWLBack<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWLBack<? super L$<? super N<? super X>>>>>,
            E<QLRBack<? super N<? super X>>> {}

    public interface QRLBack<X> {}
    public interface QWRBack<X> extends
            MR<N<? super QRBack<? super X>>>,
            ML<N<? super QWRBack<? super ML<? super N<? super X>>>>>,
            LA<N<? super QWRBack<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWRBack<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWRBack<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWRBack<? super L$<? super N<? super X>>>>>,
            E<QRLBack<? super N<? super X>>> {}

    public interface QLRCheck<X> {}
    public interface QWLCheck<X> extends
            ML<N<? super QLCheck<? super X>>>,
            MR<N<? super QWLCheck<? super MR<? super N<? super X>>>>>,
            LA<N<? super QWLCheck<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWLCheck<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWLCheck<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWLCheck<? super L$<? super N<? super X>>>>>,
            E<QLRCheck<? super N<? super X>>> {}

    public interface QRLCheck<X> {}
    public interface QWRCheck<X> extends
            MR<N<? super QRCheck<? super X>>>,
            ML<N<? super QWRCheck<? super ML<? super N<? super X>>>>>,
            LA<N<? super QWRCheck<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWRCheck<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWRCheck<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWRCheck<? super L$<? super N<? super X>>>>>,
            E<QRLCheck<? super N<? super X>>> {}

    public interface QLRHalt<X> {}
    public interface QWLHalt<X> extends
            ML<N<? super QLHalt<? super X>>>,
            MR<N<? super QWLHalt<? super MR<? super N<? super X>>>>>,
            LA<N<? super QWLHalt<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWLHalt<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWLHalt<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWLHalt<? super L$<? super N<? super X>>>>>,
            E<E<? super String>> {}

    public interface QRLHalt<X> {}
    public interface QWRHalt<X> extends
            MR<N<? super QRHalt<? super X>>>,
            ML<N<? super QWRHalt<? super ML<? super N<? super X>>>>>,
            LA<N<? super QWRHalt<? super LA<? super N<? super X>>>>>,
            LB<N<? super QWRHalt<? super LB<? super N<? super X>>>>>,
            Lx<N<? super QWRHalt<? super Lx<? super N<? super X>>>>>,
            L$<N<? super QWRHalt<? super L$<? super N<? super X>>>>>,
            E<E<? super String>> {}

    public interface QLStart<X> extends
            L$<N<? super QWLScan<? super L$<? super N<? super L$<? super N<? super MR<? super N<? super X>>>>>>>>> {}
    public interface QRStart<X> extends
            L$<N<? super QWRScan<? super L$<? super N<? super MR<? super N<? super L$<? super N<? super X>>>>>>>>> {}

    public interface QLScan<X> extends
            LB<N<? super QWLBack<? super ML<? super N<? super Lx<? super N<? super X>>>>>>>,
            L$<N<? super QWLCheck<? super L$<? super N<? super ML<? super N<? super L$<? super N<? super X>>>>>>>>>,
            LA<N<? super QWLScan<? super LA<? super N<? super MR<? super N<? super X>>>>>>>,
            Lx<N<? super QWLScan<? super Lx<? super N<? super MR<? super N<? super X>>>>>>> {}
    public interface QRScan<X> extends
            LB<N<? super QWRBack<? super Lx<? super N<? super ML<? super N<? super X>>>>>>>,
            L$<N<? super QWRCheck<? super L$<? super N<? super L$<? super N<? super ML<? super N<? super X>>>>>>>>>,
            LA<N<? super QWRScan<? super MR<? super N<? super LA<? super N<? super X>>>>>>>,
            Lx<N<? super QWRScan<? super MR<? super N<? super Lx<? super N<? super X>>>>>>> {}

    public interface QLBack<X> extends
            LA<N<? super QWLScan<? super Lx<? super N<? super MR<? super N<? super X>>>>>>>,
            LB<N<? super QWLBack<? super ML<? super N<? super LB<? super N<? super X>>>>>>>,
            Lx<N<? super QWLBack<? super ML<? super N<? super Lx<? super N<? super X>>>>>>> {}
    public interface QRBack<X> extends
            LA<N<? super QWRScan<? super MR<? super N<? super Lx<? super N<? super X>>>>>>>,
            LB<N<? super QWRBack<? super LB<? super N<? super ML<? super N<? super X>>>>>>>,
            Lx<N<? super QWRBack<? super Lx<? super N<? super ML<? super N<? super X>>>>>>> {}

    public interface QLCheck<X> extends
            L$<N<? super QWLHalt<? super L$<? super N<? super MR<? super N<? super L$<? super N<? super X>>>>>>>>>,
            Lx<N<? super QWLCheck<? super ML<? super N<? super Lx<? super N<? super X>>>>>>> {}
    public interface QRCheck<X> extends
            L$<N<? super QWRHalt<? super L$<? super N<? super ML<? super N<? super L$<? super N<? super X>>>>>>>>>,
            Lx<N<? super QWRCheck<? super Lx<? super N<? super ML<? super N<? super X>>>>>>> {}

    public interface QLHalt<X> {}
    public interface QRHalt<X> {}
}