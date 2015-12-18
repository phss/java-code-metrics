package testproject.pkg1;

public class InPackage1 {
    private AnotherInPackage1 someVar;
    public InPackage1() {
        someVar = new AnotherInPackage1();
    }
}