package StylCode_01;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class StylCode_01 {

    public static void main(String[] args) {
//        StylCode_01 stylCode_01 = new StylCode_01();
//        stylCode_01.suplerTest();


        StringBuilder sb = new StringBuilder();

        addExists(sb,false);

        sb.append("+++");

        System.out.println("sb="+sb);






    }

//    private String printProfileParameters(Map<String, String> profileParameters) {
//        Function<Long, Long> maxLengthKey = new Function<Long, Long>() {
//            private Long keyNameLength = 0L;
//
//            @Override
//            public Long apply(Long keyLength) {
//                if (keyNameLength < keyLength) {
//                    keyNameLength = keyLength;
//                }
//                return keyNameLength;
//            }
//        };
//        profileParameters.keySet().forEach(key -> {
//                if (Objects.nonNull(key)) {
//                    maxLengthKey.apply((long) key.length());
//                }
//            }
//        );
//
//        StringBuilder statement = new StringBuilder();
//        profileParameters.entrySet().forEach(profileParameter -> {
//            PrinterUtils.lsInd(statement);
//            String resourceNameFormat = "%-" + maxLengthKey.apply(-1L).toString()
//                + "s" + "  " + "%s";
//            statement.append(String.format(resourceNameFormat, profileParameter.getKey(), profileParameter.getValue()));
//        });
//        return statement.toString();
//    }

    void predicateTest() {
        Predicate<Boolean> isFirstElement = new Predicate<Boolean>() {

            private int count = 1;

            @Override
            public boolean test(Boolean c) {
                if (count == 1 ) {
                    count++;
                    return true;
                }
                return  false;
            }


        };
    }

    public void suplerTest() {
        Supplier<Boolean> isFirstElement = new Supplier<Boolean>() {
            private Boolean check = true;
            @Override
            public Boolean get() {
                if (check) {
                    check = !check;
                    return true;
                }
                return false;
            }
        };

        System.out.println(isFirstElement.get());
        System.out.println(isFirstElement.get());
        System.out.println(isFirstElement.get());
        System.out.println(isFirstElement.get());
        System.out.println(isFirstElement.get());
        System.out.println(isFirstElement.get());

    }

    public static void addExists(StringBuilder sb, boolean useExistsOption) {
//        sb.append(useExistsOption ? MetadataNodePrinter.IF_EXISTS : "");
    }


}