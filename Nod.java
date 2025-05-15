public class Nod {
    private int id ;
    private int x,y;
    private boolean visited ;

    public Nod(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visited = false;
    }
    public int getid(){  
        return id;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public boolean isVisited() {
        return visited;
    }
    public void colocavisitado(boolean visited){
        this.visited = visited;
    }
    public void setlocation(int x,int y){
        this.x = x;
        this.y = y;
    }

}

