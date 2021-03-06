package de.meisterfuu.animexx.activitys.ens;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import de.meisterfuu.animexx.R;
import de.meisterfuu.animexx.activitys.AnimexxBaseActivityAB;
import de.meisterfuu.animexx.activitys.profiles.ProfileActivity;
import de.meisterfuu.animexx.api.broker.ENSBroker;
import de.meisterfuu.animexx.api.web.ReturnObject;
import de.meisterfuu.animexx.objects.UserObject;
import de.meisterfuu.animexx.objects.ens.ENSDraftObject;
import de.meisterfuu.animexx.objects.ens.ENSObject;
import de.meisterfuu.animexx.utils.Helper;
import de.meisterfuu.animexx.utils.imageloader.PicassoDownloader;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SingleENSActivity extends AnimexxBaseActivityAB {

    long mID;
    ENSObject mENS;
    UserObject target;

    WebView mWebView;
    TextView mSubject, mUserLabel, mUser, mDate, mMessage;
    ImageView mAvatar;
    FrameLayout mHeader, mBody;
    ENSBroker mAPI;
    Boolean mLoaded = false;



    SimpleDateFormat sdf = new SimpleDateFormat("'Datum: 'HH:mm dd.MM.yyyy", Locale.getDefault());


    public static void getInstance(Context pContext, long pID) {
        Intent i = new Intent().setClass(pContext, SingleENSActivity.class);
        Bundle args = new Bundle();
        args.putLong("id", pID);
        i.putExtras(args);
        pContext.startActivity(i);
    }

    public static PendingIntent getPendingIntent(Context pContext, long pID) {
        Intent intent = new Intent(pContext, SingleENSActivity.class);
        Bundle args = new Bundle();
        args.putLong("id", pID);
        intent.putExtras(args);
        return PendingIntent.getActivity(pContext, (new Random()).nextInt(), intent, PendingIntent.FLAG_ONE_SHOT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ens);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mSubject = (TextView) this.findViewById(R.id.activity_ens_single_subject);
        mUserLabel = (TextView) this.findViewById(R.id.activity_ens_single_user_label);
        mUser = (TextView) this.findViewById(R.id.activity_ens_single_user);
        mDate = (TextView) this.findViewById(R.id.activity_ens_single_date);
        mWebView = (WebView) this.findViewById(R.id.activity_ens_single_webview);
        mMessage = (TextView) this.findViewById(R.id.activity_ens_single_message);
        mAvatar = (ImageView) this.findViewById(R.id.activity_ens_single_user_avatar);

        mHeader = (FrameLayout) this.findViewById(R.id.activity_ens_single_header);
        mBody = (FrameLayout) this.findViewById(R.id.activity_ens_single_body);

        mHeader.setVisibility(View.GONE);
        mBody.setVisibility(View.GONE);

        //this.findViewById(R.id.activity_ens_single_base);


        mWebView.setVisibility(View.GONE);


        Bundle extras = this.getIntent().getExtras();
        mID = extras.getLong("id");


        mAPI = new ENSBroker(this);
        mAPI.getENS(mID, new Callback<ReturnObject<ENSObject>>() {
            @Override
            public void success(final ReturnObject<ENSObject> t, final Response response) {
                mENS = t.getObj();

                mSubject.setText(mENS.getSubject());
                //SingleENSActivity.this.getActionBar().setTitle(mENS.getSubject());

                mDate.setText(sdf.format(mENS.getDateObject()));
                target = new UserObject();
                if (mENS.getAn_ordner() > 0) {
                    target = mENS.getReply_to();
                    if (target == null) target = mENS.getVon();
                    if (target == null) target = new UserObject();
                    mUserLabel.setText("Von:");
                    mUser.setText(target.getUsername());
                } else {
                    target = mENS.getReply_to();
                    if (target == null) {
                        if (mENS.getAn().size() > 0) {
                            target = mENS.getAn().get(0);
                        }
                    }
                    if (target == null) target = new UserObject();
                    mUserLabel.setText("An:");
                    mUser.setText(target.getUsername());
                }

                if (target.getAvatar() != null) {
                    System.out.println(target.getAvatar().getUrl());
                    PicassoDownloader.getAvatarPicasso(SingleENSActivity.this).load(target.getAvatar().getUrl()).stableKey(PicassoDownloader.createAvatarKey(target.getId())).into(mAvatar);
                } else {
                    mAvatar.setImageResource(R.drawable.ic_contact_picture);
                }

                mAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProfileActivity.getInstance(SingleENSActivity.this, target.getId());
                    }
                });

                mMessage.setText(Html.fromHtml(getFullMessage()));


                mHeader.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.VISIBLE);


                mLoaded = true;

                mAPI.clearNotification();
            }

            @Override
            public void failure(final RetrofitError error) {

            }
        });

    }


    protected String getFullMessage() {
        if (!mENS.getSignature().isEmpty()) {
            return mENS.getMessage() + "<br><br>---------------<br>" + mENS.getSignature();
        } else {
            return mENS.getMessage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.single_ens, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.answer:
                if (mLoaded) answer();
                break;
            case R.id.answer_quote:
                if (mLoaded) answerQuote();
                break;
            case R.id.forward:
                if (mLoaded) forward();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAPI.close();
    }

    private void forward() {
        final ENSDraftObject draft = new ENSDraftObject();
        draft.setMessage(mENS.getMessage());
        ArrayList<Long> recip = new ArrayList<Long>();
//        recip.add(target.getId());
        ArrayList<String> recip_name = new ArrayList<String>();
//        recip_name.add(target.getUsername());
        draft.setRecipients(recip);
        draft.setRecipients_name(recip_name);
        draft.setSubject("Fw:"+mENS.getSubject());
        draft.setReferenceID(mENS.getId());
        draft.setReferenceType("forward");
        mAPI.saveENSDraft(draft, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {
                NewENSActivity.getInstance(SingleENSActivity.this, draft);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void answer() {
        final ENSDraftObject draft = new ENSDraftObject();
        draft.setMessage("");
        ArrayList<Long> recip = new ArrayList<Long>();
        recip.add(target.getId());
        ArrayList<String> recip_name = new ArrayList<String>();
        recip_name.add(target.getUsername());
        draft.setRecipients(recip);
        draft.setRecipients_name(recip_name);
        draft.setSubject(Helper.BetreffRe(mENS.getSubject()));
        draft.setReferenceID(mENS.getId());
        draft.setReferenceType("reply");
        mAPI.saveENSDraft(draft, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {
                NewENSActivity.getInstance(SingleENSActivity.this, draft);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void answerQuote() {
        final ENSDraftObject draft = new ENSDraftObject();
        draft.setMessage(mENS.getMessage());
        ArrayList<Long> recip = new ArrayList<Long>();
        recip.add(target.getId());
        ArrayList<String> recip_name = new ArrayList<String>();
        recip_name.add(target.getUsername());
        draft.setRecipients(recip);
        draft.setRecipients_name(recip_name);
        draft.setSubject(Helper.BetreffRe(mENS.getSubject()));
        draft.setReferenceID(mENS.getId());
        draft.setReferenceType("reply");
        mAPI.saveENSDraft(draft, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {
                NewENSActivity.getInstance(SingleENSActivity.this, draft);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


}
