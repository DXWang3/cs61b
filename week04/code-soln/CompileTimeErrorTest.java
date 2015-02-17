// [ //
public class CompileTimeErrorTest {
    public string howOld(age) {
//         ^ needs to be capitalized
//                       ^ missing "int" declaration
        if age <= 18 {
//        (         ) missing parentheses
             return "Not very old";
        } else if (age > 21) {
             return "Really old";
        }
//      missing unconditional or "else" return statement here
    }
}
// ] //
