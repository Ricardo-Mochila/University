
public class Mdc{

    public int mdc(int a, int b){
        if(a == b){
            return a;
        }
        else if(b>a){
            return mdc(a,b-a);
        }
        else{
            return mdc(a-b,b);
        }
    }
    public static void main(String[] args) {

        int a = 12;
        int b = 20;

        Mdc cenas = new Mdc();

        System.out.println(cenas.mdc(a, b));
    }
}
