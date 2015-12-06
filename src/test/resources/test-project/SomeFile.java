package testproject;

public class SomeFile {

    // Example from: http://pmd.sourceforge.net/pmd-4.3.0/rules/codesize.html
       public void example()  {
           if (a == b)  {
               if (a1 == b1) {
                   fiddle();
               } else if (a2 == b2) {
                   fiddle();
               } else{
                   fiddle();
               }
           } else if (c == d) {
               while (c == d) {
                fiddle();
               }
           } else if (e == f) {
               for (int n = 0; n < h; n++) {
                   fiddle();
               }
           } else {
               switch (z) {
                   case 1:
                       fiddle();
                       break;
                   case 2:
                       fiddle();
                       break;
                   case 3:
                       fiddle();
                       break;
                   default:
                       fiddle();
                       break;
            }
        }
    }
}