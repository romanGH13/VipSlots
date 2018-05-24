package com.example.roma.vipslots;

import android.content.DialogInterface;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView fieldBet = null;
    private TextView fieldLines = null;
    private TextView fieldCoins = null;
    private TextView fieldJackpot = null;

    private RecyclerView leftColumn = null;
    private RecyclerView middleColumn = null;
    private RecyclerView rightColumn = null;

    private int jackpot = 250000;

    public static LinearLayout gameLayout = null;

    public User user = null;
    Map<String, String> combinationsDictionary = null;

    private boolean inGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldBet = (TextView)findViewById(R.id.field_bet);
        fieldLines = (TextView)findViewById(R.id.field_lines);
        fieldCoins = (TextView)findViewById(R.id.field_coins);
        fieldJackpot = (TextView)findViewById(R.id.field_jackpot);

        user = new User();

        fieldLines.setText(Integer.toString(user.getLines()));
        fieldCoins.setText(Integer.toString(user.getBalance()));
        updateJackpot(jackpot);
        updateBet();

        createCombinations();

        ImageView img = (ImageView)findViewById(R.id.image_settings);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inGame)
                    return;
                showSettingsDialog();
            }
        });

        // получаем доступный размер игрового поля и исходя их этого изменяем размер картинок
        final ConstraintLayout gameLayout = (ConstraintLayout) findViewById(R.id.game_layout);
        gameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int availableHeight = gameLayout.getMeasuredHeight();
                if(availableHeight>0) {
                    gameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    setGameFields(availableHeight, availableHeight/12);
                }
            }
        });
    }


    public void updateJackpot(int jackpot)
    {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String numberAsString = numberFormat.format(jackpot);
        fieldJackpot.setText(numberAsString);
    }

    public void updateCoins()
    {
        fieldCoins.setText(Integer.toString(user.getBalance()));
    }

    public void updateBet()
    {
        fieldBet.setText(Integer.toString(user.getBet() * user.getLines()));
    }

    public void updateLines()
    {
        fieldLines.setText(Integer.toString(user.getLines()));
    }

    /**
     * Метод заполнения основных элементов для комбинаций
     */
    private void createCombinations()
    {
        combinationsDictionary = new LinkedHashMap<String, String>();
        combinationsDictionary.put("75", "combination_6");
        combinationsDictionary.put("50", "combination_5");
        combinationsDictionary.put("35", "combination_4");
        combinationsDictionary.put("25", "combination_3");
        combinationsDictionary.put("15", "combination_2");
        combinationsDictionary.put("10", "combination_1");
    }

    /**
     * Метод генерации случайных данных
     * count - количество повторений данных
     */
    private List<String> createRandom(int count)
    {
        List<String> list = new ArrayList<>();
        list.addAll(combinationsDictionary.values());
        Collections.shuffle(list);
        for(int i=0; i<count; i++) {
            list.addAll(list);
        }

        // Генерация случайного числа для jackpot
        int random_number = (int) (Math.random() * 10);
        if(random_number == 7)
        {
            int randomIndex = (int) (Math.random() * (list.size() - 1));
            list.add(randomIndex, "combination_7");
        }
        return list;
    }

    private void setGameFields(int height, int margin)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {

                    private static final float SPEED = 200f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }

                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this) {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {

                    private static final float SPEED = 200f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }

                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this) {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {

                    private static final float SPEED = 200f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }

                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };

        leftColumn = (RecyclerView)findViewById(R.id.leftColumn);
        leftColumn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        leftColumn.setLayoutManager(layoutManager);
        leftColumn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        GameRecyclerViewAdapter leftAdapter = new GameRecyclerViewAdapter(this, createRandom(2), height);
        leftColumn.setAdapter(leftAdapter);

        middleColumn = (RecyclerView)findViewById(R.id.middleColumn);
        middleColumn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        middleColumn.setLayoutManager(layoutManager2);
        middleColumn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        GameRecyclerViewAdapter middleAdapter = new GameRecyclerViewAdapter(this, createRandom(2), height);
        middleColumn.setAdapter(middleAdapter);

        rightColumn = (RecyclerView)findViewById(R.id.rightColumn);
        rightColumn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        rightColumn.setLayoutManager(layoutManager3);
        GameRecyclerViewAdapter rightAdapter = new GameRecyclerViewAdapter(this, createRandom(2), height);
        rightColumn.setAdapter(rightAdapter);
        rightColumn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        rightColumn.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        actionEndGame();
                        break;
                }

            }
        });
    }

    private int getWinPointsPerLine(int lineIndex)
    {
        int sumWin = 0;

        GameRecyclerViewAdapter leftAdapter = (GameRecyclerViewAdapter)leftColumn.getAdapter();
        GameRecyclerViewAdapter middleAdapter = (GameRecyclerViewAdapter)middleColumn.getAdapter();
        GameRecyclerViewAdapter rightAdapter = (GameRecyclerViewAdapter)rightColumn.getAdapter();

        String itemLeft = leftAdapter.getItemByIndex(leftAdapter.getItemCount() - (4 - lineIndex));
        String itemMiddle = middleAdapter.getItemByIndex(middleAdapter.getItemCount() - (4 - lineIndex));
        String itemRight = rightAdapter.getItemByIndex(rightAdapter.getItemCount() - (4 - lineIndex));

        if(itemLeft.contentEquals(itemMiddle) && itemLeft.contentEquals(itemRight))
        {
            for(Map.Entry<String, String> entry : combinationsDictionary.entrySet()) {
                if(itemLeft.contentEquals(entry.getValue()))
                {
                    sumWin += Integer.parseInt(entry.getKey())*user.getBet();
                    break;
                }
            }
        }

        if(itemLeft.contentEquals("combination_7") && itemLeft.contentEquals(itemMiddle) && itemLeft.contentEquals(itemRight))
        {
            sumWin += 250000;
        }
        else if(itemLeft.contentEquals("combination_7") && itemLeft.contentEquals(itemMiddle))
        {
            sumWin += 50 * user.getBet();
        }
        else if(itemLeft.contentEquals("combination_7"))
        {
            sumWin += 25 * user.getBet();
        }

        return sumWin;
    }

    /**
     * Метод окончания прокрутки и выдача выиграша
     */
    private void actionEndGame()
    {
        int sumWin = 0;

        if(user.isFirstLine())
        {
            sumWin += getWinPointsPerLine(1);
        }
        if(user.isSecondLine())
        {
            sumWin += getWinPointsPerLine(2);
        }
        if(user.isThirdLine())
        {
            sumWin += getWinPointsPerLine(3);
        }


        user.addCoints(sumWin);
        updateCoins();
        if(sumWin > 0)
            showWinDialog(sumWin);
        else
        {
            jackpot+=user.getBet();
            updateJackpot(jackpot);
        }
        user.setBet(5);
        updateBet();
        inGame = false;
    }

    private void showWinDialog(int amount)
    {
        WonDialog alert = new WonDialog();
        alert.showDialog(this, Integer.toString(amount));
    }

    private void showSettingsDialog()
    {
        SettingDialog alert = new SettingDialog();
        alert.showDialog(this);
    }


    public void changeFirstLine(boolean isVisible)
    {
        ImageView line = (ImageView)findViewById(R.id.line1);
        if(isVisible)
            line.setVisibility(View.VISIBLE);
        else
            line.setVisibility(View.INVISIBLE);
        updateLines();
        updateBet();
    }
    public void changeSecondLine(boolean isVisible)
    {
        ImageView line = (ImageView)findViewById(R.id.line2);
        if(isVisible)
            line.setVisibility(View.VISIBLE);
        else
            line.setVisibility(View.INVISIBLE);
        updateLines();
        updateBet();
    }
    public void changeThirdLine(boolean isVisible)
    {
        ImageView line = (ImageView)findViewById(R.id.line3);
        if(isVisible)
            line.setVisibility(View.VISIBLE);
        else
            line.setVisibility(View.INVISIBLE);
        updateLines();
        updateBet();
    }

    /**
     * Обработчик собатия на кнопку Spin
     */
    public void eventSpin(View v) {
        if(inGame)
            return;

        if(user.getBalance() < user.getLines() * user.getBet())
            return;

        user.dismissCoints(user.getLines() * user.getBet());
        updateCoins();

        inGame = true;
        // Перемещаем в начало списка


        // Получаем адаптеры ресайклеров
        GameRecyclerViewAdapter leftAdapter = (GameRecyclerViewAdapter)leftColumn.getAdapter();
        GameRecyclerViewAdapter middleAdapter = (GameRecyclerViewAdapter)middleColumn.getAdapter();
        GameRecyclerViewAdapter rightAdapter = (GameRecyclerViewAdapter)rightColumn.getAdapter();

        // Изменяем данные в списках
        leftAdapter.setData(createRandom(2));
        middleAdapter.setData(createRandom(2));
        rightAdapter.setData(createRandom(2));

        // Уведомляем об изменении данных
        leftAdapter.notifyDataSetChanged();
        middleAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();

        leftColumn.scrollToPosition(0);
        middleColumn.scrollToPosition(0);
        rightColumn.scrollToPosition(0);

        // Плавно прокручиваем в конец списка
        leftColumn.smoothScrollToPosition(leftAdapter.getItemCount()+1);
        middleColumn.smoothScrollToPosition(middleAdapter.getItemCount()+1);
        rightColumn.smoothScrollToPosition(rightAdapter.getItemCount()+1);
    }

    /**
     * Обработчик события понижения ставки
     */
    public void eventLowerBid(View v) {
        if(inGame)
            return;
        if(fieldBet != null)
        {
            if(user.getBet() > 5)
            {
                user.setBet(user.getBet() - 5);
                updateBet();
            }
        }
    }

    /**
     * Обработчик события повышения ставки
     */
    public void eventRaiseBid(View v) {
        if(inGame)
            return;
        if(fieldBet != null)
        {
            if(((user.getBet() + 5)*user.getLines()) <= user.getBalance()){
                user.setBet(user.getBet() + 5);
                updateBet();
            }
        }
    }
}
