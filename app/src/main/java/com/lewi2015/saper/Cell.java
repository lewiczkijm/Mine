package com.lewi2015.saper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

public class Cell {
    public static final int ROWS = 9;
    public static final int COLS = 9;
    public static final int MINES = 10;

    public static Place cell[][] = new Place[ROWS][COLS];

    public static void init(Context context) { // Создание массива объектов - полей Place
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cell[i][j] = new Place(context, i, j);

            }
        }
        reInit();
    }

    public static void reInit() { //Установка мин
        int minesCounter = MINES;
        while (minesCounter > 0) {
            int y = (int) Math.abs(Math.random() * 10);
            int x = (int) Math.abs(Math.random() * 10);
            boolean flag;
            try {
                flag = cell[y][x].isMine;
            } catch (Exception e) {
                Log.d("SAPER", "error", e);
                flag = true;
            }

            if (!flag) {
                cell[y][x].isMine = true;
                minesCounter--;
            }
        }
    }

    // Открытие поля
    public static void open(int y, int x, boolean isClick) {


        //Запуск окончания игры поражением при попадании на мину и показ мины
        int result = Cell.view(y, x, false);
        Cell.cell[y][x].isOpen = true;
        cell[y][x].setEnabled(false);
        if (result == 1 && isClick) {
            cell[y][x].setBackgroundColor(Color.RED);
            cell[y][x].setText("*");
            return;
        } else {
            int count = view8(y, x, true);
            cell[y][x].setText(count + "");
            cell[y][x].setTextColor(Color.BLUE);
        }
    }

    //
    public static int view8(int y, int x, boolean flag) {
        int counter = 0;
        counter += view(y - 1, x - 1, flag);
        counter += view(y - 1, x, flag);
        counter += view(y - 1, x + 1, flag);
        counter += view(y, x - 1, flag);
        counter += view(y, x + 1, flag);
        counter += view(y + 1, x - 1, flag);
        counter += view(y + 1, x, flag);
        counter += view(y + 1, x + 1, flag);
        return counter;
    }

    //Проверка на наличие мин. Если мина есть, возвращает 1, если нет - 0.
    // Выполняет проверку существования поля и было ли оно уже открыто.
    // Если поле пустое, существует и не было открыто, вызывается open();
    public static int view(int y, int x, boolean open) {
        if (y < 0 || x < 0 || y >= Cell.ROWS || x >= Cell.COLS) {
            Log.d("SAPER_view", "wall");
            return 0;
        }
        if (Cell.cell[y][x].isOpen && open) {
            Log.d("SAPER_view", "already open");
            return 0;
        }
        if (Cell.cell[y][x].isMine) {
            Log.d("SAPER_view", "mine");
            return 1;
        }
        if (!Cell.cell[y][x].isMine) {
            Log.d("SAPER_view", "white");
            //TODO Запуск open() после отладки для раскрытия белых полей
            //
            if (!Cell.cell[y][x].isOpen && open) {
                Cell.open(y, x, false);
            }
            return 0;
        }
        Log.d("SAPER_view", "exit");
        return 0;
    }

    public static void rewrite(int y, int x, int flag) {//Перерисовка полей
        if (flag == -1) {
            cell[y][x].setBackgroundColor(Color.RED);
            cell[y][x].setText("*");
        } else if (flag == 0) {
            //
        } else if (flag > 0) {
            cell[y][x].setText(flag + "");
            cell[y][x].setTextColor(Color.BLUE);
        }
        cell[y][x].setEnabled(false);
    }
}

