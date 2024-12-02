package lab1_task2_114m;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab1_task2_114M {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = 0, K = 0, winnerInRound = 0, roundNum = 0, numWin = 0, indWin = 0, indxComp = 0;
        //ввод с клавиатуры количества игроков и кубиков
        System.out.print("Введите кол-во игроков: ");
        K = in.nextInt();
        System.out.print("Введите кол-во кубиков: ");
        N = in.nextInt();
        indxComp = N - 1; //индекс компьютера
        ArrayList<Player> players = new ArrayList<Player>(N);//массив игроков 
        for (int i = 0; i < N; i++) {
            players.add(i, new Player(0, 0));
        }
        if (K > 1 && N > 1) { //игроков должно быть больше 1
            //игра продолжается до тех пор, пока кол-во выигрышей одного из игроков не составит 7
            while (numWin != 7) { //до 7 выигрышей одного из игроков
                System.out.println("\nИгра № " + roundNum); //номер раунда

                //очистить очки предыдущего раунда у игроков
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).setsum(0); //сумма очков каждого игрока = 0
                }

                //игрок, победивший в прошлом раунде кидает первым
                players.get(winnerInRound).setsum(rollTheDice(K));
                //вывод информации об игроке
                System.out.println("Первым делает бросок: " + printInformPlayer(winnerInRound, indxComp));
                printPlayer(players, winnerInRound, indxComp); //вывод информации об игроке

                //игроки, не победившие в прошлом раунде, кидают по очереди
                for (int i = 0; i < players.size(); i++) { //ход каждого игрока
                    if (i == winnerInRound) {
                        continue; //игрок, победивший в прошлом раунде уже сделал бросок
                    }
                    players.get(i).setsum(rollTheDice(K)); //запись суммы очков в поле sumscore игрока  
                    printPlayer(players, i, indxComp);//вывод информации об игроке
                }

                //поиска игрока с самым большим кол-вом очков
                winnerInRound = playerMaxSum(players); //номер победителя в раунде
                //нашли игрока с наибольшим кол-вом очков
                players.get(winnerInRound).setwin(1); // win+=1 - игрок победил в раунде

                //вывод победителя в раунде
                System.out.println("\nПобедил: " + printInformPlayer(winnerInRound, indxComp) + " его очки: " + players.get(winnerInRound).getsum() + " кол-во его побед: " + players.get(winnerInRound).getwin());
                //наибольшее кол-во побед у какого-либо игрока
                indWin = playerWinner(players); //индекс игрока с наибольшим кол-вом побед
                numWin = players.get(indWin).getwin(); //кол-во побед
                roundNum++;
            }
            //номер победителя в игре
            System.out.println("Победитель в игре: " + printInformPlayer(indWin, indxComp) + " кол-во его побед: " + players.get(indWin).getwin());
        } else {
            System.out.println("Игроков должно быть больше 1");
        }
    }

    //метод поиска игрока с самым большим кол-вом очков
    public static int playerMaxSum(ArrayList<Player> players) {
        int maxsum = players.get(0).getsum(), winnerInRound = 0, sum = 0;
        for (int j = 1; j < players.size(); j++) { // проход по массиву игроков
            sum = players.get(j).getsum();
            if (sum > maxsum) {
                winnerInRound = j; //номер игрока с наибольшим кол-вом очков
                maxsum = sum;
            }
        }
        return winnerInRound;
    }

    //метод поиска игрока с самым большим кол-вом побед
    public static int playerWinner(ArrayList<Player> players) {
        int maxwin = players.get(0).getwin(), winnerIngame = 0, win = 0;
        for (int j = 0; j < players.size(); j++) { // проход по массиву игроков
            win = players.get(j).getwin();
            if (win > maxwin) {
                winnerIngame = j; //номер игрока с наибольшим кол-вом побед
            }
        }
        return winnerIngame;
    }

    //метод броска кубика К раз
    public static int rollTheDice(int K) {
        int step = 0, sumscore = 0;

        while (step < K) { //бросать кубик К раз
            sumscore += (int) (Math.random() * 6 + 1); //сумма очков всех бросков одного игрока
            step++; //кол-во бросков
        }
        return sumscore; //сумма осков всех бросков
    }

    //метод вывода игрока и очков
    public static void printPlayer(ArrayList<Player> players, int indx, int indxComp) {
        if (indx == 0) {
            System.out.print("У вас очков: " + players.get(indx).getsum() + " | ");
        }
        if (indx == indxComp) {
            System.out.print("У компьютера очков: " + players.get(indx).getsum() + " | ");
        }
        if (indx != 0 && indx != indxComp) {
            System.out.print("У игрока: " + indx + " очков: " + players.get(indx).getsum() + " | ");
        }
    }

    //метод вывода информации об игроке
    public static String printInformPlayer(int indx, int indxComp) {
        String str = new String();
        if (indx == 0) {
            str = "Пользователь";
        }
        if (indx == indxComp) {
            str = "Компьютер";
        }
        if (indx != 0 && indx != indxComp) {
            str = "Игрок: " + indx;
        }
        return str;
    }

    static class Player {

        private int win; //кол-во побед игрока
        private int sumscore; //сумма очков за раунд

        Player(int win, int sumscore) {
            this.win = win;
            this.sumscore = sumscore;
        }
        public void setsum(int sumscore) {
            this.sumscore = sumscore;
        }
        public int getsum() {
            return sumscore;
        }
        public void setwin(int win) {
            this.win += win;
        }
        public int getwin() {
            return win;
        }
    }

}