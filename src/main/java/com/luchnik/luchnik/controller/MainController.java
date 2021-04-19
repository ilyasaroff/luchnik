package com.luchnik.luchnik.controller;

import com.luchnik.luchnik.javaclass.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {
    // step - поля (шаги), на которые могут ходить фишки. 0 элемент - текущее положение, остальные элементы - собссно шаги
    int[][] step = {{1, 12, 8}, {2, 11, 9}, {3, 6, 14, 10}, {4, 7, 15}, {5, 8, 14},
                    {6, 13, 3}, {7, 16, 12, 4}, {8, 1, 7, 19, 9, 5}, {9, 2, 8, 14}, {10, 3, 13},
                    {11, 2, 18}, {12, 7, 1, 21, 17}, {13, 6, 22, 20, 10}, {14, 9, 3, 19, 25, 5}, {15, 4, 24},
                    {16, 7, 23}, {17, 12, 24, 18}, {18, 11, 17, 24, 18}, {18, 11, 17, 21, 25, 19}, {19, 18, 22, 14, 8}, {20, 13, 23},
                    {21, 12, 18}, {22, 13, 19}, {23, 16, 20}, {24, 17, 15}, {25, 18, 14}};
    // начальное положение 1 игрока. 0 элемент - поле фишки, 1 элемент =1, если был поражен соперник, 2 элемент - количество повторных шагов
    int[][] player1 = {{1, 0, 0}, {2, 0, 0}, {3, 0, 0}, {4, 0, 0}, {5, 0, 0}};
    // начальное положение 2 игрока. 0 элемент - поле фишки, 1 элемент =1, если был поражен соперник, 2 элемент - количество повторных шагов
    int[][] player2 = {{21, 0, 0}, {22, 0, 0}, {23, 0, 0}, {24, 0, 0}, {25, 0, 0}};
    // поля, по которым может стрелять 1 игрок
    int[][] firePlayer1 = {{1, 12, 8}, {2, 11, 9}, {3, 6, 14, 10}, {4, 7, 15}, {5, 8, 14},
                            {6, 13, 3}, {7, 16, 12, 4}, {8, 1, 7, 19, 9, 5}, {9, 2, 8, 14}, {10, 3, 13},
                            {11, 2, 18}, {12, 7, 1, 21, 17}, {13, 6, 22, 20, 10}, {14, 9, 3, 19, 25, 5}, {15, 4, 24},
                            {16, 7, 23}, {17, 12, 24, 18}, {18, 11, 17, 24, 18}, {18, 11, 17, 21, 25, 19}, {19, 18, 22, 14, 8}, {20, 13, 23}};
    // поля, по которым может стрелять 2 игрок
    int[][] firePlayer2 = {{6, 13, 3}, {7, 16, 12, 4}, {8, 1, 7, 19, 9, 5}, {9, 2, 8, 14}, {10, 3, 13},
                            {11, 2, 18}, {12, 7, 1, 21, 17}, {13, 6, 22, 20, 10}, {14, 9, 3, 19, 25, 5}, {15, 4, 24},
                            {16, 7, 23}, {17, 12, 24, 18}, {18, 11, 17, 24, 18}, {18, 11, 17, 21, 25, 19}, {19, 18, 22, 14, 8}, {20, 13, 23},
                            {21, 12, 18}, {22, 13, 19}, {23, 16, 20}, {24, 17, 15}, {25, 18, 14}};
    // поля, занимаемые 1 игроком в случае победы
    int[] winPlayer1 = {0, 21, 22, 23, 24, 25};
    // поля, занимаемые 2 игроком в случае победы
    int[] winPlayer2 = {0, 1, 2, 3, 4, 5};
    //ход игрока 1 - первый, 2-второй
    Integer player = 1;

    //Функция проверки возможности хода на позицию next
    Boolean checkStep (int next) {
        for (int i = 0; i < player1.length; i++) {
            if (player1[i][0] == next) return false;
        }
        for (int i = 0; i<player2.length; i++) {
            if (player2[i][0] == next) return false;
        }
        return true;
    }

    //Проверка на проигрыш
    Boolean checkLosePlayer (int player) {
        if (player == 1) {
            int k = 0;
            for (int i = 0; i < player1.length; i++) {
                if (player1[i][0] == 0) k++;
            }
            if (k == 5) return true;
            else return false;
        }
        if (player == 2) {
            int k = 0;
            for (int i = 0; i< player2.length; i++) {
                if (player2[i][0] == 0) k++;
            }
            if (k == 5) return true; else return false;
        }
        return false;
    }

    //Проверка на победителя
    Boolean checkWinPlayer (int player) {
        if (player == 1) {
            int k = 0;
            for (int i = 0; i < player1.length; i++) {
                for (int j = 0; j < winPlayer1.length; j++) {
                    if (player1[i][0] == winPlayer1[j]) k++;
                }
            }
            if (k == 5) return true;
            else return false;
        }
        if (player == 2) {
            int k = 0;
            for (int i = 0; i< player2.length; i++) {
                for (int j = 0; j< winPlayer2.length; j++) {
                    if (player2[i][0] == winPlayer2[j]) k++;
                }
            }
            if (k == 5) return true; else return false;
        }
        return false;
    }

    // Ход игрока
    Boolean stepPlayer (int player, int last, int next) {
        if (player==1) {
            for (int i=0; i<player1.length; i++) {
                if (player1[i][0] == last) {
                    // проверяем возможность хода игрока на last поле
                    for (int j=0; j<step.length; j++) {
                        if (last == step [j][0]) {
                            for (int k=1; k<step [j].length; k++) {
                                if (next == step[j][k]) {
                                    player1[i][0] = next;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (player==2) {
            for (int i=0; i<player2.length; i++) {
                if (player2[i][0] == last) {
                    // проверяем возможность хода игрока на last поле
                    for (int j=0; j<step.length; j++) {
                        if (last == step [j][0]) {
                            for (int k=1; k<step [j].length; k++) {
                                if (next == step[j][k]) {
                                    player2[i][0] = next;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //смена хода игрока
    void replacePlayer () {
        if (player == 1) player = 2; else player = 1;
    }

    // Стрельба по сопернику
    Boolean stepFirePlayer (int player, int next) {
        if (player == 1) {
            for (int i=0; i< firePlayer2.length; i++) {
                for (int j=1; j<firePlayer2[i].length; j++) {
                    if (firePlayer2[i][j]==next) {
                        for (int k=0; k<player2.length; k++) {
                            if (firePlayer2[i][0]==player2[k][0]) {
                                for (int l=0; l<player1.length; l++) {
                                    if (player1[l][0]==next) {
                                        player1[l][0]=0;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (player == 2) {
            for (int i=0; i< firePlayer1.length; i++) {
                for (int j=1; j<firePlayer1[i].length; j++) {
                    if (firePlayer1[i][j]==next) {
                        for (int k=0; k<player1.length; k++) {
                            if (firePlayer1[i][0]==player1[k][0]) {
                                for (int l=0; l<player2.length; l++) {
                                    if (player2[l][0]==next) {
                                        player2[l][0]=0;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Отправка инфы на страницу
    Message sendMessage (String mess) {
        Message message = new Message();
        message.setPlayer1(player1);
        message.setPlayer2(player2);
        message.setMess(mess);
        return message;
    }

    @GetMapping("/")
    public String  index() {
        return "index";
    }
    @GetMapping("/status")
    public @ResponseBody Message  status() {
        return sendMessage("Ход игрока № "+player);
    }
    @GetMapping("/step/{last}/{next}")
    public @ResponseBody Message  step(@PathVariable("last") Integer last, @PathVariable("next") Integer next) {
        //Проверяем возможность хода
        if ( checkStep(next)) {
            // проверяем ход игрока
            if (stepPlayer(player, last, next)) {
                //После хода игрока проверяем его на победу
                if (checkWinPlayer(player)) {
                    return sendMessage("Победа! Выиграл игрок №"+player);
                }
                //Выполняем стрельбу по сопернику
                if(stepFirePlayer(player, next)) {
                    //Проверяем на проигрыш соперника
                    if (checkLosePlayer(player)) {
                        return sendMessage("Проиграл игрок №"+player);
                    }
                    //-------------------------------
                    replacePlayer();
                    return sendMessage("Поражение соперника. Следующий ход игрок №"+player);
                }
                //-------------------------------
                replacePlayer();
                return sendMessage("Ход сделан. Следующий ход игрок №"+player);
            }
            return sendMessage("Ошибка хода. Ход игрок №"+player);
        }
        return sendMessage("Ошибка хода. Ход игрок №"+player);
    }
}
