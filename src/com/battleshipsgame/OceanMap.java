package com.battleshipsgame;

public class OceanMap {
    private int [][] coordinates;
    private final static int X_COORDINATE_MAX = 10;
    private final static int Y_COORDINATE_MAX = 10;

    OceanMap(){
        coordinates = new int [X_COORDINATE_MAX][Y_COORDINATE_MAX];

        printMap(coordinates);

    }

    public void setCoordinates(int [][] coordinates){
        this.coordinates = coordinates;
    }

    public int [][] coordinates(){
        return this.coordinates;
    }

    private String createWhiteSpaces(String text, int numSpace){
        String space = new String(new char[numSpace]).replace('\0', ' ');
        String fullString = space + text + space;

        return fullString;
        //System.out.println(s.format(s,builder.toString()));
    }

    private String getLabel (String text, int numSpace){
        String fullLabel = createWhiteSpaces(text,numSpace);

        return fullLabel;
    }

    public void printMap (int [][] coordinates){
        for(int i = 0; i<coordinates.length ; i++){
            //System.out.print(i + " |");
            if (i == 0) {
                System.out.println(getLabel("0123456789",3));
            }
            System.out.print(i + " |");

            for(int j = 0; j<=coordinates[i].length ;j++){
                    if(j == Y_COORDINATE_MAX) System.out.print("| " + i);
                    else System.out.print(" ");
            }
            System.out.println("");
            if (i == coordinates.length-1) System.out.println(getLabel("0123456789",3));
        }
    }

}
