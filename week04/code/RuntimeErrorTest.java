public class RuntimeErrorTest {
    private Person p;

    public RuntimeErrorTest() {
         String personName = p.getName();
         int nameLength = personName.length();
         System.out.println(nameLength);
     }

     public static void main(String[] args) {
         RuntimeErrorTest t = new RuntimeErrorTest();
     }
}

class Person {
    public String getName() {}
}
