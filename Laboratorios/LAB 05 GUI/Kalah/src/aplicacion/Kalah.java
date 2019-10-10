package aplicacion;
/**
 * A implementation of the game "Kalah"
 * 
 * @author Nicolas Aguilera && Daniel Walteros 
 * @version 11/04/2019
 */
public class Kalah{
    private int almacenPlayer1;
    private int almacenPlayer2;
    private int housesAmount;
    private int seedsAmount;
    private int[] player1;
    private int[] player2;
    private boolean turnoExtra;
    /**
     * Constructor for objects of class Kalah
     * 
     * @param houses The number of houses in the game.
     * @param seeds The numbers of seeds per house.
    */
    public Kalah(int houses,int seeds) throws KalahException{
        if(houses<1||seeds<1){throw new KalahException(KalahException.BAD_DIMENSIONS);}
        turnoExtra=false;
        almacenPlayer1 = 0;
        almacenPlayer2 = 0;
        housesAmount = houses;
        seedsAmount = seeds;
        player1 = new int[houses];
        player2 = new int[houses];
        for(int i=0;i<houses;i++){
            player1[i]=seeds;
            player2[i]=seeds;
        }
    }
    /**
     * Determinates if the CPU can play a turn in the game.
     * 
     * @return A boolean that represents if the CPU can play a turn in the game.
    */
    public boolean juegue(){
        int maxAlmacen = -1;
        int maxCasa = -1;
        for(int i=0;i<housesAmount;i++){
            int store = simuleCpu(i);
            if (store>=maxAlmacen){
                maxAlmacen = store;
                maxCasa = i+1;
            }
        }
        boolean seguir = juegueCpu(maxCasa);
		return seguir;
    }
    /**
     * Determinates if the game finishes
     * 
     * @return A boolean that represents if the game finished or not.
    */
    public boolean finish(){
		int sum1=0;
		int sum2=0;
        for(int i=0;i<housesAmount;i++){
			sum1+=player1[i];
        }
		for(int i=0;i<housesAmount;i++){
			sum2+=player2[i];
        }
		return (sum1==0)||(sum2==0);
    }
    /**
     * Determinates what player won the game.
     * 
     * @return A boolean that represents what player won the game.
    */
	public int gane(){
		if(almacenPlayer1>almacenPlayer2){
			return 2;
		}
		else if(almacenPlayer1==almacenPlayer2){
			return 1;
		}
		else{
			return 0;
		}
	}
	/**
     * Determinates if a player gets an extra turn.
     * 
     * @return A boolean that represents if a player gets an extra turn.
    */
	public boolean getTurnoExtra(){
		return turnoExtra;
	}
	/**
     * Execute a turn of a player and determine if the game ends or not
     * 
     * @return A boolean that represents  if the game ends or not.
    */
    public boolean juegue(int casa) throws KalahException{
        if (casa>housesAmount||casa<1){throw new KalahException(KalahException.BAD_HOUSE);}
        turnoExtra=false;
        int cant = player1[casa-1];
        player1[casa-1]=0;
        while (cant!=0){
            for(int i=casa;i<housesAmount;i++){
                if (cant!=0){
                    if(cant==1 && player1[i]==0){
                        player1[i] = player2[i];
                        player2[i]=0;
                    }
                    player1[i]++;
                    cant--;
                }
                else{
                    break;
                }
            }
            if(cant==0){break;}
            if(cant==1){turnoExtra=true;}
            almacenPlayer1++;
            cant--;
            if(cant==0){break;}
            for(int i=0;i<housesAmount;i++){
                if (cant!=0){
                    player2[housesAmount-i-1]++;
                    cant--;
                }
                else{
                    break;
                }
            }
            if(cant==0){break;}
            almacenPlayer2++;
            cant--;
            if(cant==0){break;}
            casa=0;
        }
        boolean finish = finish();
		return (!finish)&&turnoExtra;
    }
    /**
     * Execute a turn of the CPU and determine if the game ends or not
     * 
     * @return A boolean that represents  if the game ends or not.
    */
    private boolean juegueCpu(int casa){
        int cant = player2[casa-1];
		player2[casa-1]=0;
		turnoExtra=false;
        while (cant!=0){
            for(int j=0;j<casa-1;j++){
                if (cant!=0){
                    if(cant==1 && player2[casa-j-2]==0){
                        player2[casa-j-2] = player1[casa-j-2];
                        player1[casa-j-2]=0;
                    }
                    player2[casa-j-2]++;
                    cant--;
                }
                else{
                     break;
                }
            }
            if(cant==0){break;}
            if(cant==1){turnoExtra=true;}
            almacenPlayer2++;
            cant--;
            if(cant==0){break;}
            for(int j=0;j<housesAmount;j++){
                if (cant!=0){
                    player1[j]++;
                    cant--;
                }
                else{
                    break;
                }
            }
            if(cant==0){break;}
            almacenPlayer1++;
            cant--;
            if(cant==0){break;}
            casa=housesAmount-1;
        }
		boolean finish = finish();
		return (!finish)&&turnoExtra;
    }
    private int[] copia(int[] lista){
        int[] copia = new int[lista.length];
        for(int i=0;i<lista.length;i++){
            copia[i] = lista[i];
        }
        return copia;
    }
    /**
     * Simulates a turn of the CPU without executing it
     * 
     * @param  casa  an integer that represents the position of the house
     * where the CPU is going to play.
     * 
     * @return An integer that represents the CPU Storage at the end of that turn.
    */
    private int simuleCpu(int casa){
        int cant = player2[casa];
        int[] tempPlayer1 = copia(player1);
        int[] tempPlayer2 = copia(player2);
        int tempAlmacen2 = almacenPlayer2;
        int tempAlmacen1 = almacenPlayer1;
        while (cant!=0){
            for(int j=0;j<casa;j++){
                if (cant!=0){
                    if(cant==1 && tempPlayer2[casa-j-1]==0){
                        tempPlayer2[casa-j-1] = tempPlayer1[casa-j-1];
                        tempPlayer1[casa-j-1]=0;
                    }
                    tempPlayer2[casa-j-1]++;
                    cant--;
                }
                else{
                     break;
                }
            }
            if(cant==0){break;}
            if(cant==1){turnoExtra=true;}
            tempAlmacen2++;
            cant--;
            if(cant==0){break;}
            for(int j=0;j<housesAmount;j++){
                if (cant!=0){
                    tempPlayer1[j]++;
                    cant--;
                }
                else{
                    break;
                }
            }
            if(cant==0){break;}
            tempAlmacen1++;
            cant--;
            if(cant==0){break;}
            casa=0;
        }
        return tempAlmacen2;
    }
    /**
     * Allows you to check the status of the game boxes
     * 
     * @return  An Integer Array representing the status of the game boxes
     */
    public int[] consulte(){
        int[] valores = new int[2*housesAmount+2];
        valores[0]=almacenPlayer2;
        for(int i=0;i<housesAmount;i++){
            valores[i+1]=player2[i];
        }
        valores[housesAmount+1]=almacenPlayer1;
        for(int i=0;i<housesAmount;i++){
            valores[housesAmount+2+i]=player1[i];
        }
        return valores;
    }
    /**
     * Allows you to check the status of the player 1 game boxes
     * 
     * @return  An Integer Array representing the status of the player 1 game boxes
     */
    public int[] getBoard1(){
        return player1;
    }
    /**
     * Allows you to check the status of the player 2 game boxes
     * 
     * @return  An Integer Array representing the status of the player 2game boxes
     */
    public int[] getBoard2(){
        return player2;
    }
}
