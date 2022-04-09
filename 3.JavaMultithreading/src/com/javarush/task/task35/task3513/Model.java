package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    private void saveState(Tile[][] tiles){
        Tile[][] newTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(newTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!(previousScores.isEmpty()) && !(previousStates.isEmpty())) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }





    public Model() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if(gameTiles[i][j].value==0) return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            Tile[] tiles ;
            tiles = gameTiles[i];
            for (int j = 0; j < tiles.length; j++) {
                int tileNumber = tiles[j].value;
                if(tileNumber!=0) {
                    for (int k = j + 1; k < tiles.length; k++) {
                        int compareNumber  = tiles[k].value;
                        if (tileNumber == compareNumber){
                            return true;
                        }else if(compareNumber>0) break;
                    }

                    for (int k = i+1; k < FIELD_WIDTH; k++) {
                        int compareNumber = gameTiles[k][j].value;
                        if (tileNumber == compareNumber){
                            return true;
                        }else if(compareNumber>0) break;
                    }
                }
            }
        }
        return false;
    }

    void resetGameTiles() {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        if (getEmptyTiles().size() > 0)
            getEmptyTiles().get((int) (Math.random() * getEmptyTiles().size())).value = Math.random() < 0.9 ? 2 : 4;

    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    private boolean compressTiles(Tile[] tiles) {

        int firstNumber = Arrays.hashCode(tiles);

        Arrays.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                return (o1.value > 0  &&  o2.value > 0) ? 0 : o2.value - o1.value;
            }
        });

        return firstNumber != Arrays.hashCode(tiles);
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChange = false;
        for (int i = 0; i < tiles.length-1; i++) {
            if(tiles[i].value==tiles[i + 1].value){
                if(tiles[i].value !=0 && tiles[i+1].value !=0) isChange = true;
                int sc;
                sc = (tiles[i].value += tiles[i].value);
                if(sc>maxTile) maxTile = sc;
                score += sc;
                tiles[i+1].value = 0;
                compressTiles(tiles);
            }
        }
        return isChange;
    }

    public void left(){
        if(isSaveNeeded) saveState(gameTiles);

        boolean isChange = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            compressTiles(gameTiles[i]);
            if(mergeTiles(gameTiles[i])){
                    isChange = true;
                }
            }
        if(isChange){addTile();}
        addTile();
        isSaveNeeded = true;
        }


        public void right(){
            saveState(gameTiles);

            Tile[][] tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = Arrays.copyOf(gameTiles[i],gameTiles[i].length);
            }

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    gameTiles[i][j] = tempArray[i][((FIELD_WIDTH-1))-j];
                }
            }

            left();

            tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = Arrays.copyOf(gameTiles[i],gameTiles[i].length);
            }

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    gameTiles[i][j] = tempArray[i][((FIELD_WIDTH-1))-j];
                }
            }

        }

        public void up(){
            saveState(gameTiles);
            Tile[][] tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = Arrays.copyOf(gameTiles[i],gameTiles[i].length);
            }

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    gameTiles[i][j] = tempArray[j][(FIELD_WIDTH-1)-i];
                }
            }

            left();

            tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = Arrays.copyOf(gameTiles[i],gameTiles[i].length);
            }

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    gameTiles[i][j] = tempArray[(FIELD_WIDTH-1)-j][i];
                }
            }

        }
        public void down(){
            saveState(gameTiles);
            Tile[][] tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = Arrays.copyOf(gameTiles[i],gameTiles[i].length);
            }

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    gameTiles[i][j] = tempArray[(FIELD_WIDTH-1)-j][i];
                }
            }

            left();

            tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = Arrays.copyOf(gameTiles[i],gameTiles[i].length);
            }
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    gameTiles[i][j] = tempArray[j][(FIELD_WIDTH-1)-i];
                }
            }

        }

        void randomMove(){
        int n;
        switch (n = ((int) (Math.random() * 100)) % 4){
            case 0: up();break;
            case 1: down();break;
            case 2: right();break;
            case 3: left();break;
            }
            System.out.println(n);
        }

        boolean hasBoardChanged(){
        int curenttile=0, previoustile=0;
        Tile[][] tile = previousStates.peek();
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    curenttile+=gameTiles[i][j].value;
                    previoustile+=tile[i][j].value;
                }
            }
            return curenttile!=previoustile;
        }

    MoveEfficiency getMoveEfficiency(Move move){
        move.move();

        if(!hasBoardChanged()){
            rollback();
            return new MoveEfficiency(-1,0,move);
        }
        rollback();
        return new MoveEfficiency(getEmptyTiles().size(),score,move);
    }
    void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4,Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.peek().getMove().move();

    }
    }

