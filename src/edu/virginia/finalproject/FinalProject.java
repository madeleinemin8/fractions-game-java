package edu.virginia.finalproject;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Question;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FinalProject extends Game {
    Sprite ev = new Sprite("evangelina", "evangelina1.png");
    Sprite you = new Sprite("player", "you.png");

    Sprite choice1 = new Sprite("choice1", "sugar1.png");
    Sprite choice2 = new Sprite("choice2", "sugar2.png");
    Sprite choice3 = new Sprite("choice3", "sugar3.png");
    Sprite choice4 = new Sprite("choice4", "sugar4.png");

    DisplayObject badge1 = new DisplayObject("badge1", "badge1.png");
    DisplayObject badge2 = new DisplayObject("badge2", "badge2.png");
    DisplayObject badge3 = new DisplayObject("badge3", "badge3.png");

    ArrayList<Sprite> choices = new ArrayList<Sprite>();
    ArrayList<Question> qArray = new ArrayList<Question>();
    ArrayList<String> intros = new ArrayList<String>();
    DisplayObject background = new DisplayObject("background", "background.png");

    boolean canMove = false;
    boolean canAdvance = true;
    boolean gameOver = false;
    boolean incorrect = false;
    boolean levelswitch = true;
    boolean entering = false;

    Point pos1 = new Point(20, 80);
    Point pos2 = new Point(100, 110);
    Point pos3 = new Point(175, 110);
    Point pos4 = new Point(290, 60);
    Point bpos1 = new Point(0, 2);
    Point bpos2 = new Point(65, 5);
    Point bpos3 = new Point(140, 2);
    Point youpos = new Point(300, 190);
    Point mixingPoint = new Point(450, 45);

    int questionNumber = -1;
    int attempts = 1;
    int level = 1;
    int introcount = 0;
    //SoundManager sm;
    public FinalProject(){
        super("Final Project", 1200, 815);
        ev.setPosition(new Point(400, 100));
        you.setPosition(youpos);
        you.setHasPhysics(true);

        choice1.setPosition(pos1);
        choice2.setPosition(pos2);
        choice3.setPosition(pos3);
        choice4.setPosition(pos4);
        choices.add(choice1);
        choices.add(choice2);
        choices.add(choice3);
        choices.add(choice4);

        badge1.setPosition(bpos1);
        badge2.setPosition(bpos2);
        badge3.setPosition(bpos3);
        badge1.setAlpha(0f);
        badge2.setAlpha(0f);
        badge3.setAlpha(0f);

        intros.add("For our first recipe, let's make Pan de Muerto, my daughter’s favorite!");
        intros.add("You'll use the arrow keys to move to the ingredient you want.");
        intros.add("Click the Next button at the bottom to get started!");
        intros.add("Now, we’re going to make Tortillas de Cempazuchitl (Marigold Tortillas)!");
        intros.add("Uh oh! Looks like the recipe is too big! Let’s make half of the recipe instead!");
        intros.add("Now we’re going to make Atole de Vainilla, a warm vanilla flavored drink.");
        intros.add("Oh no! It looks like I lost some of my measuring cups.\r\n\r\n\tCan you help me convert my measurements?");

                // LEVEL ONE QUESTIONS
        qArray.add(new Question("Can you find the half (1/2) bowl of sugar?",
                "Not quite, the correct amount is this one!\r\n\r\n\tSee how the sugar fills 1 of 2 parts of the bowl, so it is 1/2 (one half)!",
                "", 1));
        qArray.add(new Question("Can you find me a fourth (1/4) bowl of sugar?",
                "Not quite, the correct amount is this one!\r\n\r\n\tThe sugar fills 1 of 4 parts of the bowl, so it is 1/4 (one fourth)!",
                "", 3));
        qArray.add(new Question("Can you find me a third (1/3) bowl of sugar?",
                "Not quite, the correct amount is this one!\r\n\r\n\tThe sugar fills 1 of 3 parts of the bowl, so it is 1/3, or one third!",
                "", 2));
        qArray.add(new Question("Can you find me an eighth (1/8) bowl of sugar?",
                "Not quite, the correct amount is this one! \r\n\r\n\tThe sugar fills 1 of 8 parts of the bowl, so it is 1/8, or one eighth!",
                "",4));
        qArray.add(new Question("Can you find me a half (1/2) stick of butter?",
                "Oops, that’s not quite it. One half stick of butter is here!\r\n\r\n\tSee how it the lines on the butter divide it into two parts and 1/2 is one of those parts?",
                "",3));
        qArray.add(new Question("Can you find me a fourth (1/4) stick of butter?",
                "\r\n\r\n\tOops, not quite! 1/4 looks like one of four parts",
                "", 4));
        qArray.add(new Question("Can you find me a third (1/3) bag of flour?",
                "Not quite! 1/3 of a bag is shown here. \r\n\r\n\tSee how it is one of three parts of the whole?",
                "", 3));
        qArray.add(new Question("Can you find me an eighth (1/8) cup of milk?",
                "Not quite...One eighth of a cup is here! \r\n\r\n\tSee how the lines on the cup divide it into eight parts and 1/8 is one of those parts?",
                "",4));

        // LEVEL FOUR QUESTIONS
        qArray.add(new Question());
        qArray.add(new Question("Can you get me half of 1/4 cup of milk?",
                "It’s actually ⅛ cup of milk. \r\n" +
                "\tWe can figure this out by multiplying ¼  * ½. \r\n" +
                "\tFirst, we multiply the numerators (top numbers) 1*1=1, that’s our new numerator\r\n" +
                "\tNext, we multiply the denominators (bottom numbers). 4*2=8, that’s our new denominator",
                "Oops, not quite it. Remember we can think of half of ¼ as ½ * ¼ .",
                4));
        qArray.add(new Question("Can you get me half of 2/3 a bag of flour?",
                "It’s actually ⅓ or 2/6  bag of flour. We get this by multiplying ⅔  * ½. \n" +
                "\tFirst, to get our new numerator (top number), we multiply the numerators (2*1). \r\n" +
                "\tSecond to get our new denominator we multiply our denominators (3*2). \r\n" +
                "\tLastly, we have 2/6, which we can simplify by dividing the numerator and the denominator by 2. 2/6 = ⅓.",
                "Oops, not quite it. Half of 2/3 a bag of flour is the same as ½ * ⅔ a bag of flour.",
                3));
        qArray.add(new Question("Let’s make our tortillas extra buttery! Can you find 3 times a 1/4 stick of butter?",
                "The correct amount is 3/4. We find this by multiplying 3/1 * 1/4 just like how we multiplied before.",
                "The correct amount is ¾. We find this by multiplying 3/1 * ¼ just like how we multiplied the other fractions.",
                2));
        qArray.add(new Question("Let’s add 2 times the normal amount of marigold flowers. Can you find me 2 times 1/2 a marigold flower?",
                "\tThat’s actually just 1 marigold flower. We can find that by multiplying 2/1 * ½ and we get 2/2. 2/2 the same as 1!",
                "Not quite, 2 times ½ a marigold flower is the same as 2/1 * 1/2 marigold flowers",
                1));
        qArray.add(new Question("Let’s make the tortillas extra sweet!\r\n\tCan you find 2 times a 1/8 bowl of sugar?",
                "The correct amount is 2/8 or 1/4. We find this by multiplying 2/1 * 1/8.\r\n\tThen, we simplify it by dividing the numerator and denominator by 2.",
                "Oops, not quite it. Try to think of 2 as 2/1.",
                3));


        // LEVEL 5 QUESTIONS
        qArray.add(new Question());
        qArray.add(new Question("I need a ½ cup of milk, but I don’t have a ½ measuring cup!" +
                "\r\n\tHow many times do I need to use a ¼ measuring cup instead?",
                "Actually, we need to use two ¼ measuring cups." +
                "\r\n\tTo find this, we multiply both the numerator and denominator of ½ by 2:" +
                "\r\n\t(1*2)/(2*2)=2/4. So, 2/4 is the same as ½!",
                "Oh not quite! Remember multiplying the numerator and denominator of a fraction by the same number gives you an equal fraction.",
                3));
        qArray.add(new Question("I need to measure ⅓ tablespoons of vanilla, but I only have 1/6 tablespoons." +
                "\r\n\tHow many ⅙ tablespoons fit into 1/3?", "Actually, we need to use two 1/6 measuring tablespoons." +
                "\r\n\tTo find this, we multiply both the numerator and denominator of 1/3 by 2:" +
                "\r\n\t(1*1)/(2*3)=2/6. So, 2/6 is the same as 1/3!",
                "Here’s a hint: Think about what you could multiply 3 by to get 6.",
                4));
        qArray.add(new Question("I need to measure 1/2 cup of corn flour, but I only have 1/16 measuring cup." +
                "\r\n\tHow many 1/16 measuring cups fit into 1/2 cup?",
                "Actually, we need to use eight 1/16 measuring cups." +
                        "\r\n\tTo find this, we multiply both the numerator and denominator of 1/2 by 8:" +
                        "\r\n\t(1*8)/(2*8)= 8/16. So, 8/16 is the same as ½",
                "I’ll give you a hint. 2*8 = 16!",
                1));
        qArray.add(new Question("I need to measure 2/3 a tablespoon of cinnamon, but I only have 1/12 tablespoons.\r\n\tHow many 1/12 tablespoons fit into 2/3?",
                "Actually, we need to use eight 1/12 measuring tablespoons.\r\n\tTo find this, we multiply both the numerator and denominator of 2/3 by 4:\r\n\t(2*4)/(3*4)= 8/12. So, 8/12 is the same as 2/3!",
                "Hint: What number multiplied by 3 equals 12?",
                1));
        qArray.add(new Question("I need to measure 1/3 tablespoon of salt, but I only have 1/12 tablespoons.\r\n\tHow many 1/12 tablespoons fit into 1/3?",
                "Actually, we need to use four 1/12 tablespoons.\r\n\tTo find this, we multiply both the numerator and denominator of 1/3 by 4:\r\n\t(1*4)/(3*4)= 4/12. So, 4/12 is the same as 1/3!",
                "What number multiplied by 3 equals 12?", 3));
        // add sound later
        /*sm = new SoundManager();
        sm.LoadMusic("song.mid");
        sm.PlayMusic();*/
    }
    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);
        if (you == null) return;
        if(this.canMove == true){
            if (pressedKeys.contains(KeyEvent.VK_UP)){
                you.setPosition(new Point(you.getPosition().x,
                        you.getPosition().y - 4));
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)){
                you.setPosition(new Point(you.getPosition().x - 4,
                        you.getPosition().y));
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)){
                you.setPosition(new Point(you.getPosition().x + 4,
                        you.getPosition().y));
            }
            if (pressedKeys.contains(KeyEvent.VK_DOWN)){
                you.setPosition(new Point(you.getPosition().x,
                        you.getPosition().y + 4));
            }
        }
        if (!this.canAdvance) {
            Question q = new Question();
            if(!levelswitch){ q = qArray.get(questionNumber); }
            if(you.collidesWith(choice1)) { tryAnswer(q,1); }
            else if(you.collidesWith(choice2)){ tryAnswer(q,2); }
            else if(you.collidesWith(choice3)){ tryAnswer(q, 3); }
            else if(you.collidesWith(choice4)){ tryAnswer(q, 4); }
            else{}
        }
    }

    public void nextIntro() {
        if(this.introcount < this.intros.size()){
            setPrompt(this.intros.get(this.introcount));
            if(this.introcount == 1){
                this.background.setImage("backgroundarrows.png");
                this.canMove = true;
            }
            if(introcount == 2){
                this.background.setImage("background.png");
                this.canMove = false;
            }
        }
        this.introcount++;
    }
    public void nextQuestion(){
        resetPositions();
        incorrect = false;
        questionNumber++;
        if(questionNumber == 8){
            setPrompt("Great work, you made Pan de Muerto! Let's start the next recipe!");
            canAdvance = true;
            this.button.setVisible(true);
            this.button.setText("Next Level");
            this.badge1.setAlpha(1f);
            this.level++;
            this.levelswitch = true;
            nextIntro();
        }
        else if(questionNumber == 14){
            setPrompt("Excellent! Those marigold tortillas look delicious! Let's make the last recipe!");
            canAdvance = true;
            this.button.setVisible(true);
            this.button.setText("Next Level");
            badge2.setAlpha(1f);
            this.level++;
            this.levelswitch = true;
            this.pos1 = new Point(10, 200);
            this.pos2 = new Point(50, 60);
            this.pos4.y = this.pos4.y - 30;
        }
        else if (questionNumber == 20){
            setPrompt("EXCELLENT JOB! You've cooked everything we need for the celebration!");
            this.background.setImage("backgroundfinal.png");
            this.choice1.setAlpha(0f);
            this.choice2.setAlpha(0f);
            this.choice3.setAlpha(0f);
            this.choice4.setAlpha(0f);
            this.you.setAlpha(0f);
            this.ev.setAlpha(0f);
            this.badge1.setImage("badge1.png");
            this.badge2.setImage("badge2.png");
            this.badge3.setImage("badge3.png");
            this.badge3.setAlpha(1f);
            this.levelswitch = true;
            this.canAdvance = false;
            this.canMove = false;
        }
        else {
            setPrompt(qArray.get(questionNumber).getPrompt());
            this.canMove = true;
            this.canAdvance = false;
        }
        if(questionNumber == 4){ changeImages("butter"); }
        if(questionNumber == 6){ changeImages("flour"); }
        if(questionNumber == 7){ changeImages("cup"); }
        if(questionNumber == 10){ changeImages("flour"); }
        if(questionNumber == 11){ changeImages("butter"); }
        if(questionNumber == 12){ changeImages("marigold"); }
        if(questionNumber == 13){ changeImages("sugar"); }
        if(questionNumber == 15){ changeImages("fourthcup"); }
        if(questionNumber == 16){ changeImages("sixthtablespoon"); }
        if(questionNumber == 17){ changeImages("sixteencup"); }
        if(questionNumber == 18){ changeImages("twelvetablespoon"); }
    }

    public void tryAnswer(Question q, int a){
        you.setPosition(youpos);
        if(q.getCorrect() == a){
            int random = (int)(Math.random() * 3);
            if (random == 0)
                setPrompt("That's correct, great job!");
            else if(random == 1)
                setPrompt("Perfect! You're a master chef!");
            else
                setPrompt("That's exactly right! Nice work!");
            this.canAdvance = true;
            this.button.setVisible(true);
        }
        else {
            if(this.attempts == 1) { // display hint, let them try again.
                if(this.qArray.get(questionNumber).getHint() != ""){
                    setPrompt(this.qArray.get(questionNumber).getHint() + "\r\n\t"+ q.getPrompt());
                }
                else {
                    if(q.getCorrect() < a)
                        setPrompt("Not quite...Maybe try something bigger? \r\n\t"+ q.getPrompt());
                    else
                        setPrompt("Not quite...Maybe try something smaller? \r\n\t" + q.getPrompt());
                    resetPositions();
                }
                this.attempts++;
            }
            else { // they've gotten it wrong twice, give them the answer
                setPrompt(q.getAnswer());
                this.textarea.setText("\r\n\t" + q.getAnswer() + "\r\n");
                you.setPosition(youpos);
                choices.get(q.getCorrect()-1).setPosition(this.mixingPoint);
                this.canAdvance = true;
                this.button.setVisible(true);
                this.canMove = false;
                this.incorrect = true;
            }
        }
    }

    public void resetPositions(){
        choice1.setPosition(pos1);
        choice2.setPosition(pos2);
        choice3.setPosition(pos3);
        choice4.setPosition(pos4);
        you.setPosition(youpos);
    }

    public void changeImages(String imageName){
        choice1.setImage(imageName + "1" + ".png");
        choice2.setImage(imageName + "2" + ".png");
        choice3.setImage(imageName + "3" + ".png");
        choice4.setImage(imageName + "4" + ".png");
    }

    public void setPrompt(String s) {
        this.textarea.setText("\r\n \r\n\t" + s + "\r\n\r\n");
    }

    public void advance(){
        if(levelswitch && (introcount != 3) && (introcount != 5)){
            nextIntro();
        }
        else {
            levelswitch = false;
            this.canMove = true;
            this.canAdvance = false;
            this.button.setVisible(false);
            if(incorrect = true){ attempts = 1; }
            nextQuestion();
        }
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        if(background != null){
            background.draw(g);
        }
        if(ev != null && you != null) {
            ev.draw(g);
            you.draw(g);
        }
        /*if(you != null) {

        }*/
        if(choice1 != null){
            choice1.draw(g);
        }
        if(choice2 != null){
            choice2.draw(g);
        }
        if(choice3 != null){
            choice3.draw(g);
        }
        if(choice4 != null){
            choice4.draw(g);
        }
        if(badge1 != null){
            badge1.draw(g);
        }
        if(badge2 != null){
            badge2.draw(g);
        }
        if(badge3 != null){
            badge3.draw(g);
        }
    }

    public static void main(String[] args) {
        FinalProject game = new FinalProject();
        game.setPrompt("Welcome to Division of the Dead! Click the button below to start!");
        game.button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(game.canAdvance){
                    game.advance();
                }
                else {
                    game.button.setVisible(false);
                }
                game.button.setText("\r\nNext\r\n");
            }
        });
        game.start();
    }
}
