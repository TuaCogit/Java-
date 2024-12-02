package lab1;
 
public class Lab1 {
    
    
    public static void main(String[] args) {
        
        QuadraticEquation qe = new QuadraticEquation();
        QuadraticEquation.Discriminant discriminant = qe.new Discriminant();
        qe.scan();
        discriminant.calc();
    }
    
}
