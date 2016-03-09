package carwash;

public class CarFIFO { //anv�nds inte
	int counter = 0;
	int[] array;
	
	public CarFIFO(int array){
		this.array = new int[array];
		
		for(int i = 0;i<array;i++){
			add(i);
		}
	}
	
	private void add(int id){
		CarFactory f = new CarFactory();
		array[counter]=f.createCar(id);
		counter++;		
	}
	
	private void remove(){
		
	}
}
