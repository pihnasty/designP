package tests.nestedClasses;


public class NestedClasses {
    public static void main(String[] args) {

    }
}


interface Strategy {

    double decision(double _s, double _t);

    static class MethodDefault {
         double r(double _s, double _t, double _s0, double _t0, double g) {
            return _s - _s0 - g * (_t - _t0);
        }
    }

    public class MethodDefault2 {
        double r2(double _s, double _t, double _s0, double _t0, double g) {
            return _s - _s0 - g * (_t - _t0);
        }
    }


}


class Strategy01 implements Strategy {
    Strategy.MethodDefault methodDefault;
    public Strategy01 () {
        methodDefault = new Strategy.MethodDefault();
    }

    @Override
    public double decision(double _s, double _t) {
        double _r = methodDefault.r(_s, _t, 1, 1, 1);

        double r2 = methodDefault.r(1,1,1,1,1);

        return 0;
    }
}