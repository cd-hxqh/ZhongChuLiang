package com.zcl.hxqh.zhongchuliang.view.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zcl.hxqh.zhongchuliang.R;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/12/5.
 */
public class NFC_Activity extends BaseActivity {

    private ImageView backImg;//返回
    private TextView titleText;//标题
    NfcAdapter nfcAdapter;
    private String[][] nfctechfilter = new String[][] { new String[] { NfcA.class.getName() } };
    private PendingIntent nfcintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_activity);

        backImg = (ImageView) findViewById(R.id.img_back);
        titleText = (TextView) findViewById(R.id.txt_title);
        backImg.setVisibility(View.VISIBLE);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleText.setText("NFC感应");
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(NFC_Activity.this, "该手机不支持NFC!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(NFC_Activity.this, "请在系统设置中先启用NFC功能!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        nfcintent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, nfcintent, null, nfctechfilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    //字符序列转换为16进制字符串
    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        {
//            //取出封装在intent中的TAG
//            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            byte[] id = tagFromIntent.getId();
//            try {
//                String isoString = new String(id, "UTF-8");
//                Toast.makeText(NFC_Activity.this, isoString, Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent();
//
//                Log.i("isoString=",isoString);
//                intent1.putExtra("cardnum", isoString);
//                NFC_Activity.this.setResult(1, intent1);
//                finish();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }



            String action = intent.getAction();
            if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                    || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                    || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                NdefMessage[] msgs;
                if (rawMsgs != null) {
                    msgs = new NdefMessage[rawMsgs.length];
                    for (int i = 0; i < rawMsgs.length; i++) {
                        msgs[i] = (NdefMessage) rawMsgs[i];
                    }
                } else {
                    // Unknown tag type
                    byte[] empty = new byte[0];
                    byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
                    Parcelable tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                    byte[] payload = dumpTagData(tag).getBytes();
                    NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload);
                    NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
                    msgs = new NdefMessage[] { msg };
                }
                // Setup the views
//                buildTagViews(msgs);
            }
        }

    }


    private String dumpTagData(Parcelable p) {
        StringBuilder sb = new StringBuilder();
        Tag tag = (Tag) p;
        byte[] id = tag.getId();
        sb.append("Tag ID (dec): ").append(getDec(id)).append("\n");
        Intent intent1 = new Intent();
        intent1.putExtra("cardnum", sb.toString());
        NFC_Activity.this.setResult(1, intent1);
        finish();
        return sb.toString();
    }


    private long getDec(byte[] bytes) {
        long result = 0;
        long factor = 1;
        for (int i = 0; i < bytes.length; ++i) {
            long value = bytes[i] & 0xffl;
            result += value * factor;
            factor *= 256l;
        }
        return result;
    }

}
