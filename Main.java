import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        Scanner input, counter;
        try{
            // input = new Scanner(System.in);
            input = new Scanner(new File("input.txt"));
            counter = new Scanner(new File("input.txt"));
        } catch (Exception e){
            return;
        }

        int x = 0, y = 0;
        String line;
        line = counter.nextLine();
        x = line.split(",").length;
        y++;
        while(counter.hasNextLine()){
            y++;
            counter.nextLine();
        }

        boolean[][] checked = new boolean[y][x];
        boolean[][] hasPerson = new boolean[y][x];

        for(int i = 0; i < y; i++){
            line = input.nextLine();
            String[] parts = line.split(",");
            for(int j = 0; j < x; j++){
                if(parts[j].equals("1")) hasPerson[i][j] = true;
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                helper(res, hasPerson, checked, i, j, true);
            }
        }
        Collections.sort(res);
        int sum = 0;
        System.out.print(res.size() + " teams of [");
        for(int i = 0; i < res.size()-1; i++){
            sum += res.get(i);
            System.out.print(res.get(i) + ", ");
        }
        sum += res.get(res.size()-1);
        System.out.print(res.get(res.size()-1));
        System.out.print("] totaling " + sum + ".");
    }
    private static void helper(List<Integer> res, boolean[][] hasPerson, boolean[][] checked, int y, int x, boolean firstVisit){
        if(x < 0 || y < 0 || y >= hasPerson.length || x >= hasPerson[0].length) return;
        else if(!hasPerson[y][x]) return;
        else if(checked[y][x]) return;
        // invariant: space has undeteceted person
        if(firstVisit) res.add(1);
        else {
            int temp = res.remove(res.size()-1);
            res.add(temp+1);
        }
        checked[y][x] = true;
        helper(res, hasPerson, checked, y+1, x, false);
        helper(res, hasPerson, checked, y-1, x, false);
        helper(res, hasPerson, checked, y, x+1, false);
        helper(res, hasPerson, checked, y, x-1, false);
    }
}
