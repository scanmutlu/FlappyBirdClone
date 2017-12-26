	package com.canmutlu.flappybird;

	import com.badlogic.gdx.ApplicationAdapter;
	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.graphics.GL20;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.BitmapFont;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.scenes.scene2d.Stage;

    import java.awt.TextField;
	import java.util.Random;

	public class FlappyBird extends ApplicationAdapter {
		SpriteBatch batch;
		Texture background;
		Texture[] birds;
		Texture topTube;
		Texture bottomTube;
		int flapState =0;
		int counter = 0;
		float birdY = 0;
		int velocity = 0 ;
		int gameState = 0;
		BitmapFont font;
		String yourScore;
		int TubeX = 1000;
		int TubeX_2 = 1300;
		int TubeX_3 = 1600;
		Random r;
		int tubeRandom = 0;
		int tubeRandom2 = 0 ;
		int tubeRandom3 = 0;
		int score = 0;

        public void startTubePosition(){

            TubeX = Gdx.graphics.getWidth()+topTube.getWidth()/2;
            TubeX_2 = TubeX+Gdx.graphics.getWidth()/3+topTube.getWidth()/2;
            TubeX_3 = TubeX_2+Gdx.graphics.getWidth()/3+topTube.getWidth()/2;
            gameState = 0;
            birdY =Gdx.graphics.getHeight()/2-birds[0].getHeight()/2;
            score = 0;
            r = new Random();
            tubeRandom = r.nextInt(Gdx.graphics.getHeight()- 650) + 650;
            tubeRandom2 = r.nextInt(Gdx.graphics.getHeight()- 650) + 650;
            tubeRandom3 = r.nextInt(Gdx.graphics.getHeight()- 650) + 650;

        }

		@Override
		public void create () {
			batch = new SpriteBatch();
			background = new Texture("bg.png");
			birds = new Texture[2];
			birds[0] = new Texture("bird.png");
			birds[1] = new Texture("bird2.png");
			birdY = Gdx.graphics.getHeight()/2-birds[0].getHeight()/2;
			topTube = new Texture("toptube.png");
			bottomTube = new Texture("bottomtube.png");
			font = new BitmapFont();
			startTubePosition();
            score = 0;
            yourScore = "Score" + score;
		}



		@Override
		public void render () {

			if(Gdx.input.justTouched()){

				gameState =1;
			}



			if(flapState == 0 && counter%10 == 0){
				flapState =1;
			}else{
				flapState = 0;
			}

			if(gameState == 0){
			batch.begin();
			batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			batch.draw(birds[flapState],Gdx.graphics.getWidth()/2-birds[flapState].getWidth()/2,birdY);
				batch.draw(topTube,TubeX ,1000);
				batch.draw(bottomTube,TubeX , -700);
                yourScore = "Score" + score;
                font.setColor(1.0f,1.0f,1.0f,1.0f);
                font.getData().setScale(5f);
                font.draw(batch,yourScore,25,100);


			counter++;

			batch.end();
			}else{
				if(Gdx.input.justTouched()){
					velocity =-25;
				}

				if(birdY>0 ||  velocity<0  ){
					velocity ++ ;
					birdY -= velocity;

				}

				batch.begin();
				batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				batch.draw(birds[flapState],Gdx.graphics.getWidth()/2-birds[flapState].getWidth()/2,birdY);
				batch.draw(topTube,TubeX,tubeRandom);
				batch.draw(bottomTube,TubeX,tubeRandom-1700);
				batch.draw(topTube,TubeX_2,tubeRandom2);
				batch.draw(bottomTube,TubeX_2,tubeRandom2-1700);
				batch.draw(topTube,TubeX_3,tubeRandom3);
				batch.draw(bottomTube,TubeX_3,tubeRandom3-1700);
                yourScore = "Score = " + score;
                font.setColor(1.0f,1.0f,1.0f,1.0f);
                font.draw(batch,yourScore,25,100);
				TubeX-=4;
				TubeX_2-=4;
				TubeX_3-=4;
				if(TubeX < Gdx.graphics.getWidth()/2 && TubeX > Gdx.graphics.getWidth()/2 -bottomTube.getWidth()){
					if(birdY < tubeRandom-100 && birdY > tubeRandom-1700 + bottomTube.getHeight()){
						score++;
					}else{
						Gdx.app.log("GAME OVER", "GAME OVER");
						Gdx.graphics.setContinuousRendering(false);
                        yourScore = "Game Over !!" + "\n" + "Your Score is = " + score;
                        font.setColor(1.0f,1.0f,1.0f,1.0f);
                        font.draw(batch,yourScore,Gdx.graphics.getWidth()/2 - 200,Gdx.graphics.getHeight()/2+200);

                        if (Gdx.input.justTouched()) {
                            startTubePosition();
                            Gdx.graphics.setContinuousRendering(true);
                        }
					}
				}


				if(TubeX_2 < Gdx.graphics.getWidth()/2  && TubeX_2 > Gdx.graphics.getWidth()/2 -bottomTube.getWidth()){
					if(birdY < tubeRandom2-100 && birdY > tubeRandom2-1700 + bottomTube.getHeight()){
                       score++;
					}else{
						Gdx.app.log("GAME OVER", "GAME OVER");

						Gdx.graphics.setContinuousRendering(false);

                        yourScore = "Game Over !!" + "\n" + "Your Score is = " + score;
                        font.setColor(1.0f,1.0f,1.0f,1.0f);
                        font.draw(batch,yourScore,Gdx.graphics.getWidth()/2 - 200,Gdx.graphics.getHeight()/2+200);


                        if (Gdx.input.justTouched()) {
                            startTubePosition();
                            Gdx.graphics.setContinuousRendering(true);
                        }
					}
					Gdx.app.log("Score", String.valueOf(score));
					Gdx.app.log("TUBES", String.valueOf(tubeRandom));

				}

				if(TubeX_3 < Gdx.graphics.getWidth()/2  && TubeX_3 > Gdx.graphics.getWidth()/2 -bottomTube.getWidth()){
					if(birdY < tubeRandom3 -100&& birdY > tubeRandom3-1700 + bottomTube.getHeight()){
                        score++;
					}else{
						Gdx.app.log("GAME OVER", "GAME OVER");
						Gdx.graphics.setContinuousRendering(false);

                        yourScore = "Game Over !!" + "\n" + "Your Score is = " + score;
                        font.setColor(1.0f,1.0f,1.0f,1.0f);
                        font.draw(batch,yourScore,Gdx.graphics.getWidth()/2 - 200,Gdx.graphics.getHeight()/2+200);


                        if (Gdx.input.justTouched()) {
                            startTubePosition();
                            Gdx.graphics.setContinuousRendering(true);
                        }
					}
					Gdx.app.log("Score", String.valueOf(score));
					Gdx.app.log("TUBES", String.valueOf(tubeRandom));
				}






				if(TubeX <=-topTube.getWidth() || TubeX_2<=-topTube.getWidth() || TubeX_3<=-topTube.getWidth()){
					if(TubeX<=-topTube.getWidth()) {
						TubeX = Gdx.graphics.getWidth();
						tubeRandom = r.nextInt(Gdx.graphics.getHeight() - 650) + 650;
						Gdx.app.log("heighTop", String.valueOf(tubeRandom));
					}
					if(TubeX_2<=-topTube.getWidth()){
						TubeX_2 = Gdx.graphics.getWidth();
						tubeRandom2 = r.nextInt(Gdx.graphics.getHeight() - 650) + 650;
						Gdx.app.log("heighTop", String.valueOf(tubeRandom));
					}
					if(TubeX_3<=-topTube.getWidth()){
						TubeX_3 = Gdx.graphics.getWidth();
						tubeRandom3 = r.nextInt(Gdx.graphics.getHeight() - 650) + 650;
						Gdx.app.log("heighTop", String.valueOf(tubeRandom));
					}
				}
				counter++;





				batch.end();



			}



		}

		@Override
		public void dispose () {
			batch.dispose();
		}
	}
