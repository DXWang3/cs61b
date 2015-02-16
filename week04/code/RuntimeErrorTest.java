public class RuntimeErrorTest {
    private Person p;

    public Test() {
         String personName = p.getName();
         int nameLength = personName.length();
         System.out.println(nameLength);
     }

     public static void main(String[] args) {
         Test t = new Test();
     }
}

class Person {
    public String getName() {}
}
