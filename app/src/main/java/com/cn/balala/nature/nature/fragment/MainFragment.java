package com.cn.balala.nature.nature.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.adapter.ToolAdapter;
import com.cn.balala.nature.nature.model.IndexInfoModel;
import com.cn.balala.nature.nature.model.ToolModel;
import com.cn.balala.nature.nature.network.MainClient;
import com.cn.balala.nature.nature.network.RequestListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    GridView gvTools;

    ConvenientBanner convenientBanner;//顶部广告栏控件
    ArrayList<Integer> localImages = new ArrayList<Integer>();

    private List<String> networkImages;
    private String[] images = {"http://p3.so.qhimg.com/t014e7febcac8f81043.jpg",
            "http://p0.so.qhimg.com/t01b5cfc7ba818053d0.jpg",
            "http://p0.so.qhimg.com/t01b059c1789b4175d0.jpg"
    };


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gvTools = (GridView) view.findViewById(R.id.gv_tools);


        List<ToolModel> list = new ArrayList<>();
        list.add(new ToolModel(R.drawable.ic_task, "会议"));
        list.add(new ToolModel(R.drawable.ic_report, "工作报告"));
        list.add(new ToolModel(R.drawable.ic_news, "新闻动态"));
        list.add(new ToolModel(R.drawable.ic_leave, "请假"));
        list.add(new ToolModel(R.drawable.ic_expense_account, "报销"));
        list.add(new ToolModel(R.drawable.ic_check, "审批"));
        list.add(new ToolModel(R.drawable.ic_tel_book, "通讯录"));
        list.add(new ToolModel(R.drawable.ic_note, "备忘录"));
        list.add(new ToolModel(R.drawable.ic_more, "更多"));

        ToolAdapter adapter = new ToolAdapter(getActivity(), list);
        gvTools.setAdapter(adapter);

        convenientBanner = (ConvenientBanner) view.findViewById(R.id.banner);

        MainClient.getIndexInfo(getActivity(), new MainClient.IndexInfoRequestModel("f9a893a94d1c1225014d1c5620bc0001"),
                IndexInfoModel.class, new RequestListener() {
                    @Override
                    public void onSuccess(Object responseModel) {

                        IndexInfoModel model = (IndexInfoModel) responseModel;

                        networkImages.clear();
                        for (IndexInfoModel.BannerInfo item : model.bannerList) {
                            networkImages.add(item.bannerUrl);
                        }

                        init();

                        Toast.makeText(getActivity(), model.bannerList.toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFail(Object responseModel) {
                        Toast.makeText(getActivity(), "首页加载失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void init() {
        initImageLoader();
        //网络加载例子
        networkImages = Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_banner_dot_not_choose, R.drawable.ic_banner_dot_choose});
    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity().getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    private class LocalImageHolderView implements CBPageAdapter.Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击事件
                    Toast.makeText(view.getContext(), "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class NetworkImageHolderView implements CBPageAdapter.Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            imageView.setImageResource(R.drawable.ic_leave);//TODO default pic
            ImageLoader.getInstance().displayImage(data, imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击事件
                    Toast.makeText(view.getContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
