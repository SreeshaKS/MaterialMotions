package com.sreesha.android.materialmotions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    CardView mProfileContainerCardView;
    CardView mProfileImageCardView;
    CardView mHeaderCardView;

    CardView mScaleTestCardView;
    CardView mRoundCardView;

    CardView mListCard[];

    RelativeLayout mBurgerIconRelativeLayout;
    CardView mArrowCard1;
    CardView mArrowCard2;
    CardView mArrowCard3;
    CardView mBurgerRippleCard;

    CardView phoneIconCardView;
    CardView outerRippleCardView;
    CardView innerRippleCardView;
    ImageView phoneIconImageView;

    float previousX = 0.0f;
    float previousY = 0.0f;
    float currentX = -10.0f;
    float currentY = -10.0f;

    HorizontalScrollView mHorizontalScrollView;
    FrameLayout mScaleTestFrameLayout;

    FrameLayout mTopCardFrameLayout;
    FrameLayout mBottomCardFrameLayout;
    int burgerIconState = 0;

    View calendarViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeViewElements();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton revertFab = (FloatingActionButton) findViewById(R.id.refreshFab);
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    /*AnimatorSet mImageHeaderAnimatorSet = getImageHeaderAnimatorSet();
                    mImageHeaderAnimatorSet.start();*/

                    playFinalAnimationForScaleTest();

                }
            });
        if (revertFab != null)
            revertFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        playInitialAnimation();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void playPhoneIconVibrationAnimation() {

        outerRippleCardView.setVisibility(View.INVISIBLE);
        innerRippleCardView.setVisibility(View.INVISIBLE);

        AnimatorSet mVibratorScaleAnimSet = new AnimatorSet();
        ObjectAnimator mPhoneImageAnimatorX
                = ObjectAnimator.ofFloat(phoneIconImageView, "translationX"
                , -10, 10, -10, 10, -10, 10, -10, 10
                , -10, 10, -10, 10, -10, 10, -10, 10
                , -10, 10, -10, 10, -10, 10, -10, 10
                , -10, 10, -10, 10, -10, 10, -10, 10, 0);
        ObjectAnimator mPhoneImageAnimatorScaleX
                = ObjectAnimator.ofFloat(phoneIconImageView, "scaleX", 1.0f, 1.2f);
        ObjectAnimator mPhoneImageAnimatorScaleY
                = ObjectAnimator.ofFloat(phoneIconImageView, "scaleY", 1.0f, 1.2f);
        innerRippleCardView.setVisibility(View.VISIBLE);
        ObjectAnimator mInnerRippleCardAnimatorScaleX
                = ObjectAnimator.ofFloat(innerRippleCardView, "scaleX", 0.2f, 1.1f);
        ObjectAnimator mInnerRippleCardAnimatorScaleY
                = ObjectAnimator.ofFloat(innerRippleCardView, "scaleY", 0.2f, 1.1f);
        ObjectAnimator mInnerRippleCardAnimatorAlpha
                = ObjectAnimator.ofFloat(innerRippleCardView, "alpha", 0.5f, 0f);
        mVibratorScaleAnimSet.playTogether(
                mPhoneImageAnimatorX
                , mPhoneImageAnimatorScaleX
                , mPhoneImageAnimatorScaleY
                , mInnerRippleCardAnimatorScaleX
                , mInnerRippleCardAnimatorScaleY
                , mInnerRippleCardAnimatorAlpha);
        mVibratorScaleAnimSet.setDuration(1000);
        mVibratorScaleAnimSet.start();
        mVibratorScaleAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                AnimatorSet mOuterRippleAnimSet = new AnimatorSet();
                outerRippleCardView.setVisibility(View.VISIBLE);
                ObjectAnimator mOuterRippleCardAnimatorScaleX
                        = ObjectAnimator.ofFloat(outerRippleCardView, "scaleX", 0.2f, 1.2f);
                ObjectAnimator mOuterRippleCardAnimatorScaleY
                        = ObjectAnimator.ofFloat(outerRippleCardView, "scaleY", 0.2f, 1.2f);
                ObjectAnimator mOuterRippleCardAnimatorAlpha
                        = ObjectAnimator.ofFloat(outerRippleCardView, "alpha", 0.5f, 0f);
                ObjectAnimator mPhoneImageAnimatorScaleX
                        = ObjectAnimator.ofFloat(phoneIconImageView, "scaleX", 1.2f, 1.0f);
                ObjectAnimator mPhoneImageAnimatorScaleY
                        = ObjectAnimator.ofFloat(phoneIconImageView, "scaleY", 1.2f, 1.0f);
                mOuterRippleAnimSet.playTogether(
                        mOuterRippleCardAnimatorScaleX
                        , mOuterRippleCardAnimatorScaleY
                        , mOuterRippleCardAnimatorAlpha
                        , mPhoneImageAnimatorScaleX
                        , mPhoneImageAnimatorScaleY
                );
                mOuterRippleAnimSet.setDuration(1000);
                mOuterRippleAnimSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void playPhoneIconAnimation() {
        playPhoneIconVibrationAnimation();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void playBurgerIconAnimation() {
        final AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator mBurgerIconAnimatorRX
                = ObjectAnimator
                .ofFloat(mBurgerIconRelativeLayout, "rotation", 0, 180);
        ObjectAnimator mArrowCard1AnimatorRX
                = ObjectAnimator
                .ofFloat(mArrowCard1, "rotation", 0, 45);
        ObjectAnimator mArrowCard3AnimatorRX
                = ObjectAnimator
                .ofFloat(mArrowCard3, "rotation", 0, -45);

        double translateXVal = (mArrowCard1.getWidth() - Math.sqrt(mArrowCard1.getWidth() / 2.0)) / 4.0;

        ObjectAnimator mArrowCard1AnimatorTX
                = ObjectAnimator
                .ofFloat(mArrowCard1, "translationX", 0, (float) translateXVal);
        ObjectAnimator mArrowCard3AnimatorTX
                = ObjectAnimator
                .ofFloat(mArrowCard3, "translationX", 0, (float) translateXVal);

        animSet.playTogether(mBurgerIconAnimatorRX
                , mArrowCard1AnimatorRX
                , mArrowCard3AnimatorRX
                , mArrowCard1AnimatorTX
                , mArrowCard3AnimatorTX
                , getRippleAnimationSet());
        animSet.setDuration(1000);
        animSet.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void playBurgerIconReverseAnimation() {
        final AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator mBurgerIconAnimatorRX
                = ObjectAnimator
                .ofFloat(mBurgerIconRelativeLayout, "rotation", 180, 360);
        ObjectAnimator mArrowCard1AnimatorRX
                = ObjectAnimator
                .ofFloat(mArrowCard1, "rotation", 45, 0);
        ObjectAnimator mArrowCard3AnimatorRX
                = ObjectAnimator
                .ofFloat(mArrowCard3, "rotation", -45, 0);

        double translateXVal = (mArrowCard1.getWidth() - Math.sqrt(mArrowCard1.getWidth() / 2.0)) / 4.0;

        ObjectAnimator mArrowCard1AnimatorTX
                = ObjectAnimator
                .ofFloat(mArrowCard1, "translationX", (float) translateXVal, 0);
        ObjectAnimator mArrowCard3AnimatorTX
                = ObjectAnimator
                .ofFloat(mArrowCard3, "translationX", (float) translateXVal, 0);


        animSet.playTogether(mBurgerIconAnimatorRX
                , mArrowCard1AnimatorRX
                , mArrowCard3AnimatorRX
                , mArrowCard1AnimatorTX
                , mArrowCard3AnimatorTX

                , getRippleAnimationSet()
        );
        animSet.setDuration(1000);
        animSet.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    AnimatorSet getRippleAnimationSet() {
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator mRippleCardAnimatorSX
                = ObjectAnimator
                .ofFloat(mBurgerRippleCard, "scaleX", 1, mBurgerIconRelativeLayout.getWidth());
        ObjectAnimator mRippleCardAnimatorSY
                = ObjectAnimator
                .ofFloat(mBurgerRippleCard, "scaleY", 1, mBurgerIconRelativeLayout.getWidth());
        ObjectAnimator mRippleCardAnimatorA
                = ObjectAnimator
                .ofFloat(mBurgerRippleCard, "alpha", 1, 0);
        animSet.playTogether(mRippleCardAnimatorSX
                , mRippleCardAnimatorSY
                , mRippleCardAnimatorA);
        return animSet;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void playFinalAnimationForScaleTest() {
        final AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator mScaleCardAnimatorSX
                = ObjectAnimator
                .ofFloat(mScaleTestCardView, "scaleX", 0.5f, 1.0f);
        ObjectAnimator mScaleCardAnimatorSY
                = ObjectAnimator.ofFloat(mScaleTestCardView, "scaleY", 0.5f, 1.0f);
        ObjectAnimator mRoundCardViewAnimatorX
                = ObjectAnimator
                .ofFloat(mRoundCardView, "translationX", 0, -200);
        /*Replicates Arc Motion*/
        mRoundCardViewAnimatorX.setInterpolator(/*new DecelerateInterpolator(1.0f)*/
                new FastOutSlowInInterpolator());
        ObjectAnimator mRoundCardViewAnimatorY
                = ObjectAnimator.ofFloat(mRoundCardView, "translationY", 0, -90);

        animSet.playTogether(mScaleCardAnimatorSX
                , mScaleCardAnimatorSY, mRoundCardViewAnimatorX, mRoundCardViewAnimatorY);
        animSet.setDuration(1000);
        Animator.AnimatorListener mListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHorizontalScrollView.setVisibility(View.VISIBLE);

                ObjectAnimator mListCardAnimatorX;
                for (CardView card : mListCard) {
                    mListCardAnimatorX = ObjectAnimator
                            .ofFloat(card, "translationX", -700, 0);
                    mListCardAnimatorX.setInterpolator(new AccelerateInterpolator(1.1f));
                    mListCardAnimatorX.setDuration(800);
                    mListCardAnimatorX.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
        animSet.addListener(mListener);
        animSet.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void playInitialAnimation() {
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator mScaleCardAnimatorSX
                = ObjectAnimator
                .ofFloat(mScaleTestCardView, "scaleX", 1f, 0.5f);
        ObjectAnimator mScaleCardAnimatorSY
                = ObjectAnimator.ofFloat(mScaleTestCardView, "scaleY", 1f, 0.5f);
        animSet.playTogether(mScaleCardAnimatorSX
                , mScaleCardAnimatorSY);
        animSet.setDuration(1000);
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animSet.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    AnimatorSet getImageHeaderAnimatorSet() {
        ObjectAnimator mProfileCardAnimatorX
                = ObjectAnimator
                .ofFloat(mProfileImageCardView, "translationX", 0, -210);
        ObjectAnimator mProfileCardAnimatorY
                = ObjectAnimator.ofFloat(mProfileImageCardView, "translationY", 0, -500);


        AnimatorSet mImageAnimatorSet = new AnimatorSet();
        mImageAnimatorSet.playTogether(mProfileCardAnimatorX
                , mProfileCardAnimatorY);
        mImageAnimatorSet.setDuration(1000);
        mImageAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHeaderCardView.setVisibility(View.VISIBLE);
                mHeaderCardView.setPivotX(50);
                ObjectAnimator mHeaderCardAnimator
                        = ObjectAnimator.ofFloat(mHeaderCardView, "alpha"
                        , 0
                        , 1
                );
                mHeaderCardAnimator.setDuration(1000);
                mHeaderCardAnimator.setInterpolator(new AccelerateInterpolator(1.1f));
                mHeaderCardAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return mImageAnimatorSet;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void playCalendarInitialAnimation() {
        AnimatorSet mAnimSet = new AnimatorSet();
        mTopCardFrameLayout.setPivotX((float) (mTopCardFrameLayout.getWidth() / 2.0));
        ObjectAnimator topCardAnimatorRX = ObjectAnimator
                .ofFloat(mTopCardFrameLayout, "rotationX", 0, -30);
        ObjectAnimator topCardAnimatorTY = ObjectAnimator
                .ofFloat(mTopCardFrameLayout, "translationY", 0, mTopCardFrameLayout.getHeight());
        ObjectAnimator bottomCardAnimatorRX = ObjectAnimator
                .ofFloat(mBottomCardFrameLayout, "rotationX", 0, 30);
        ObjectAnimator bottomCardAnimatorTY = ObjectAnimator
                .ofFloat(mBottomCardFrameLayout, "translationY", 0, -55);
        mAnimSet.playTogether(topCardAnimatorRX, bottomCardAnimatorRX, bottomCardAnimatorTY/*,topCardAnimatorTY*/);
        mAnimSet.start();
    }

    private void initializeViewElements() {
        mHeaderCardView = (CardView) findViewById(R.id.headerCard);

        mListCard = new CardView[3];
        mListCard[0] = (CardView) findViewById(R.id.listCard1);
        mListCard[1] = (CardView) findViewById(R.id.listCard2);
        mListCard[2] = (CardView) findViewById(R.id.listCard3);

        mScaleTestCardView = (CardView) findViewById(R.id.scaleTestCard);
        mRoundCardView = (CardView) findViewById(R.id.roundCard);

        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.cardListScrollView);
        mScaleTestFrameLayout = (FrameLayout) findViewById(R.id.scaleTestFrameLayout);

        mTopCardFrameLayout = (FrameLayout) findViewById(R.id.topCalCardFrameLayout);
        mBottomCardFrameLayout = (FrameLayout) findViewById(R.id.bottomCalCardFrameLayout);
        calendarViewLayout = findViewById(R.id.calendarViewLayout);
        calendarViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playCalendarInitialAnimation();
            }
        });

        mBurgerIconRelativeLayout = (RelativeLayout) findViewById(R.id.burgerIconRelativeLayout);
        mArrowCard1 = (CardView) findViewById(R.id.arrowCard1);
        mArrowCard2 = (CardView) findViewById(R.id.arrowCard2);
        mArrowCard3 = (CardView) findViewById(R.id.arrowCard3);

        mBurgerRippleCard = (CardView) findViewById(R.id.rippleCard);
        mBurgerIconRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (burgerIconState == 0) {
                    playBurgerIconAnimation();
                    burgerIconState = 1;
                } else {
                    playBurgerIconReverseAnimation();
                    burgerIconState = 0;
                }
            }
        });
        mScaleTestCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playFinalAnimationForScaleTest();
            }
        });

        phoneIconCardView = (CardView) findViewById(R.id.phoneIconCardView);
        phoneIconImageView = (ImageView) findViewById(R.id.phoneIconImageView);

        outerRippleCardView = (CardView) findViewById(R.id.outerRippleCardView);
        innerRippleCardView = (CardView) findViewById(R.id.innerRippleCardView);
        phoneIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPhoneIconAnimation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
