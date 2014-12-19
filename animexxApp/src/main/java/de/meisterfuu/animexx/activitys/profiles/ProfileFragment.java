package de.meisterfuu.animexx.activitys.profiles;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import de.meisterfuu.animexx.R;
import de.meisterfuu.animexx.objects.profile.ProfileObject;
import de.meisterfuu.animexx.utils.imageloader.ImageDownloaderCustom;
import de.meisterfuu.animexx.utils.imageloader.ImageSaveObject;
import de.meisterfuu.animexx.utils.views.FitImageView;
import de.meisterfuu.animexx.utils.views.TableDataView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private static final String USER_ID = "mUserID";

    private long mUserID;

    private FrameLayout commonFrame, contactFrame;
    private TableDataView commonTable, contactTable;
    private ImageView profileImage;
    boolean created = false;

//	private UserApi mApi;

    private ImageDownloaderCustom mImageLoader;
    private ProfileObject mUser;

    public static ProfileFragment newInstance(long pUserID) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putLong(USER_ID, pUserID);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserID = getArguments().getLong(USER_ID);
        }

        mImageLoader = new ImageDownloaderCustom("profilbild");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = (ImageView) v.findViewById(R.id.activity_profile_single_image);

        commonFrame = (FrameLayout) v.findViewById(R.id.activity_profile_single_common);
        contactFrame = (FrameLayout) v.findViewById(R.id.activity_profile_single_contact);

        commonTable = (TableDataView) v.findViewById(R.id.activity_profile_single_common_table);
        contactTable = (TableDataView) v.findViewById(R.id.activity_profile_single_contact_table);

        v.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        created = true;

        if (mUser != null) {
            onCallback(mUser);
            mUser = null;
        } else {
            ProfileObject parentProfile = ((ProfileActivity) this.getActivity()).getProfile();
            if (parentProfile != null) {
                onCallback(parentProfile);
            }
        }
//		mApi.getProfile(mUserID, this);
    }

    public void onCallback(final ProfileObject pObject) {

        if (!created) {
            mUser = pObject;
            return;
        }


        commonTable.clear();
        contactTable.clear();

        if (!pObject.getPictures().isEmpty()) {
            mImageLoader.download(new ImageSaveObject(pObject.getPictures().get(0).getUrl(), mUserID + "_0"), profileImage);
            this.profileImage.setVisibility(View.VISIBLE);
        } else {
            this.profileImage.setVisibility(View.GONE);
        }

        commonTable.clear();
        commonTable.add(new TableDataView.TableDataEntity(pObject.getUsername(), R.drawable.ic_action_ens_new));

        for (ProfileObject.ProfileContactEntry entry : pObject.getContactData()) {
            contactTable.add(new TableDataView.TableDataEntity(entry.getName() + ": " + entry.getValue(), -1));
        }
        if (pObject.getContactData().isEmpty()) {
            this.contactFrame.setVisibility(View.GONE);
        } else {
            this.contactFrame.setVisibility(View.VISIBLE);
        }


        this.getView().setVisibility(View.VISIBLE);
    }
}
