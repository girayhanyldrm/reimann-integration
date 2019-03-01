import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main{
	//The function of the given first equation.  "(x^2-x+3)"
	public static double equation1(double x){
		return Math.abs(x*x-x+3);
	}
	//The function of the given second equation.  "(3sin(x)-4)^2"
	public static double equation2(double x){
		return Math.abs(Math.pow(3*Math.sin(x)-4, 2));
	}
	//This is factorial function.
	public static double faktoriyel(double x){
		if(x==0) return 1;
		else if(x==1)
			return 1;
		else;			
			double faktoriyel=1;
			for(int i=1; i<=x; i++){
				faktoriyel=faktoriyel*i;
        }
		return faktoriyel;
	}
	//The function I created using the "ğ�‘�ğ�‘Ÿğ�‘�ğ�‘ ğ�‘–ğ�‘›â„�(ğ�‘¥)= Î£(âˆ’1)ğ�‘›(2ğ�‘›)!4ğ�‘›(ğ�‘›!)2(2ğ�‘›+1)ğ�‘¥2ğ�‘›+1"
	public static double arcSinh(String givenNumber){											
		double givenNumber2=Double.parseDouble(givenNumber);
		double valueArcsinh=0;
		for(int initialPoint=0;initialPoint<30;initialPoint++){
			double value=(faktoriyel(2*initialPoint)*Math.pow(givenNumber2, 2*initialPoint+1))/(Math.pow(4,initialPoint)*Math.pow(faktoriyel(initialPoint), 2)*(2*initialPoint+1));
			if(initialPoint%2==1)
				value*=(-1);
			valueArcsinh+=value;
		}
		return valueArcsinh;
	}
	//Riemann midpoint of sum.
	//I tried to calculate the nearest integral values â€‹â€‹by finding the area of â€‹â€‹these rectangular parts by dividing the area we want to calculate into rectangular pieces.
	public static double riemannEq1(String startPoint,String finishPoint,String sensitivity){
		double startPoint2=Double.parseDouble(startPoint);										//The function I applied the Riemann integral 
		double finishPoint2=Double.parseDouble(finishPoint);									//for the given first equation.
		double sensitivity2=Double.parseDouble(sensitivity);		
		double dikdortgenGenisligi=(finishPoint2-startPoint2)/ sensitivity2;
		double totalArea=0;
		for(double a=startPoint2+(dikdortgenGenisligi/2);a<finishPoint2;a+=dikdortgenGenisligi){
			totalArea+=dikdortgenGenisligi*equation1(a);
		}
		return totalArea;
	}
	public static double riemannEq2(String startPoint,String finishPoint,String sensitivity){
		double startPoint2=Double.parseDouble(startPoint);										//The function I applied the Riemann integral 
		double finishPoint2=Double.parseDouble(finishPoint);									//for the given second equation.
		double sensitivity2=Double.parseDouble(sensitivity);		
		double dikdortgenGenisligi=(finishPoint2-startPoint2)/ sensitivity2;
		double totalArea=0;
		for(double a=startPoint2+(dikdortgenGenisligi/2);a<finishPoint2;a+=dikdortgenGenisligi){
			totalArea+=dikdortgenGenisligi*equation2(a);
		}
			return totalArea;
	}
	public static double riemannEq3(String startPoint,String finishPoint,String sensitivity){
		double startPoint2=Double.parseDouble(startPoint);										//The function I applied the Riemann integral
		double finishPoint2=Double.parseDouble(finishPoint);									//for the given third equation.
		double sensitivity2=Double.parseDouble(sensitivity);		
		double dikdortgenGenisligi=(finishPoint2-startPoint2)/ sensitivity2;
		double totalArea=0;
		for(double a=startPoint2+(dikdortgenGenisligi/2);a<finishPoint2;a+=dikdortgenGenisligi){
			String h=Double.toString(a);
			totalArea+=dikdortgenGenisligi*Math.abs(arcSinh(h));
		}	
		return totalArea;
	}
	public static int controlArmstrong(int sayi)
	 {
	  int length = String.valueOf(sayi).length();					//Function that I check whether a number is Armstrong number.
	  int totalArmstrongPoint=0;
	  while( sayi != 0 )
	  {
	   totalArmstrongPoint +=Math.pow(sayi % 10,length);			//I took the number of digits as the base
	   sayi = sayi / 10;											//and the number of digits in the incoming count.
	  }
	  return totalArmstrongPoint;
	 }	
		
	 public static void getPoint(String stepPoint){
		 System.out.print("Armstrong "+stepPoint+" Result: ");
		 double stepPoint2=Double.parseDouble(stepPoint);			
		 //All numbers in the given number of digits the function I send to the control for the number of Armstrongs.
		 for(int initialPoint=0;initialPoint<Math.pow(10, stepPoint2);initialPoint++){				
			if(initialPoint==controlArmstrong(initialPoint)){
				System.out.print(initialPoint+" ");
				}
		 }
	 }
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("input.txt"));	 	//The section I read the given file.
			while(scanner.hasNextLine()){
					String line = scanner.nextLine();
					String [] inputFile=line.split(" ");
					if(inputFile[1].equalsIgnoreCase("Func1"))
						//Return true => Call reimannEq1 function.
						System.out.println("IntegrateRiemann Func1 "+ inputFile[2]+" "+ inputFile[3]+" "+inputFile[4]+" "+"Result: "+riemannEq1(inputFile[2],inputFile[3],inputFile[4]));
					else if(inputFile[1].equalsIgnoreCase("Func2"))
						//Return true => Call reimannEq2 function.
						System.out.println("IntegrateRiemann Func2 "+ inputFile[2]+" "+ inputFile[3]+" "+inputFile[4]+" "+"Result: "+riemannEq2(inputFile[2],inputFile[3],inputFile[4]));
					else if(inputFile[1].equalsIgnoreCase("Func3"))
						//Return true => Call reimannEq3 function.
						System.out.println("IntegrateRiemann Func3 "+ inputFile[2]+" "+ inputFile[3]+" "+inputFile[4]+" "+"Result: "+riemannEq3(inputFile[2],inputFile[3],inputFile[4]));
					else if(inputFile[0].equalsIgnoreCase("Arcsinh"))
						//Return true => Call arcSinh function.
						System.out.println("Arcsinh "+ inputFile[1] +" Result: "+arcSinh(inputFile[1]));
					else if(inputFile[0].equalsIgnoreCase("Armstrong"))
						//Return true => Call getPoint function.
						getPoint(inputFile[1]);
			}			
			scanner.close();
		} 
		catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
			}
		}
}
