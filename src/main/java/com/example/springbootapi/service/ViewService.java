package com.example.springbootapi.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * View Handle class.
 *
 * @author Naoto Wada
 *
 */
@Service
public class ViewService {

    /**
     * change
     * @param view
     * @return
     */
    public String getSceneName(String view) {
        if (StringUtils.isEmpty(view)) {
            return "top";
        }
        return VIEW.getHtmlName(view);
    }

    /**
     * Scene Enums
     *
     * @author Naoto Wada
     */
    enum VIEW {
        TOP("top", "トップ画面"), //
        INDEX("index", "プロトタイプ"), //
        JAPAN__MAP("japan", "全国地図版"),;//

        private final String html;
        private final String scene;

        private VIEW(String html, String scene) {
            this.html = html;
            this.scene = scene;
        }

        public static String getHtmlName(String scene) {
            for (VIEW view : VIEW.values()) {
                if (view.scene.equals(scene)) {
                    return view.html;
                }
            }
            throw new IllegalArgumentException("入力の画面が不正です:" + scene);
        }
    }
}