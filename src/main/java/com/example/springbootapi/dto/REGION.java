package com.example.springbootapi.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public enum REGION {
    Hokkaidō("Hokkaidō", "北海道", "北海道", "2130037"), //
    Aomori("Aomori-ken", "青森", "東北", "2130656"), //
    Iwate("Iwate-ken", "岩手", "東北", "2112518"), //
    Miyagi("Miyagi-ken", "宮城", "東北", "2111888"), //
    Akita("Akita-ken", "秋田", "東北", "2113124"), //
    Yamagata("Yamagata-ken", "山形", "東北", "2110554"), //
    Fukushima("Fukushima-ken", "福島", "東北", "2112922"), //
    Ibaraki("Ibaraki-ken", "茨城", "関東", "2112669"), //
    Tochigi("Tochigi-ken", "栃木", "関東", "1850310"), //
    Gunma("Gunma-ken", "群馬", "関東", "1863501"), //
    Saitama("Saitama-ken", "埼玉", "関東", "1853226"), //
    Chiba("Chiba-ken", "千葉", "関東", "2113014"), //
    Tokyo("Tokyo", "東京", "関東", "1850147"), //
    Kanagawa("Kanagawa-ken", "神奈川", "関東", "1860291"), //
    Niigata("Niigata-ken", "新潟", "中部", "1855429"), //
    Toyama("Toyama-ken", "富山", "中部", "1849872"), //
    Ishikawa("Ishikawa-ken", "石川", "中部", "1861387"), //
    Fukui("Fukui-ken", "福井", "中部", "1863983"), //
    Yamanashi("Yamanashi-ken", "山梨", "中部", "1848649"), //
    Nagano("Nagano-ken", "長野", "中部", "1856210"), //
    Gifu("Gifu-ken", "岐阜", "中部", "1863640"), //
    Shizuoka("Shizuoka-ken", "静岡", "中部", "1851715"), //
    Aichi("Aichi-ken", "愛知", "中部", "1865694"), //
    Mie("Mie-ken", "三重", "近畿", "1857352"), //
    Shiga("Shiga-ken", "滋賀", "近畿", "1852553"), //
    Kyoto("Kyoto", "京都", "近畿", "1857910"), //
    Ōsaka("Ōsaka-fu", "大阪", "近畿", "1853904"), //
    Hyōgo("Hyōgo-ken", "兵庫", "近畿", "1862047"), //
    Nara("Nara-ken", "奈良", "近畿", "1855608"), //
    Wakayama("Wakayama-ken", "和歌山", "近畿", "1848938"), //
    Tottori("Tottori-ken", "鳥取", "中国", "1849890"), //
    Shimane("Shimane-ken", "島根", "中国", "1852442"), //
    Okayama("Okayama-ken", "岡山", "中国", "1854381"), //
    Hiroshima("Hiroshima-ken", "広島", "中国", "1862413"), //
    Yamaguchi("Yamaguchi-ken", "山口", "中国", "1848681"), //
    Tokushima("Tokushima-ken", "徳島", "四国", "1850157"), //
    Kagawa("Kagawa-ken", "香川", "四国", "1860834"), //
    Ehime("Ehime-ken", "愛媛", "四国", "1864226"), //
    Kōchi("Kōchi-ken", "高知", "四国", "1859133"), //
    Fukuoka("Fukuoka-ken", "福岡", "九州", "1863958"), //
    Saga("Saga-ken", "佐賀", "九州", "1853299"), //
    Nagasaki("Nagasaki-ken", "長崎", "九州", "1856156"), //
    Kumamoto("Kumamoto-ken", "熊本", "九州", "1858419"), //
    Ōita("Ōita-ken", "大分", "九州", "1854484"), //
    Miyazaki("Miyazaki-ken", "宮崎", "九州", "1856710"), //
    Kagoshima("Kagoshima-ken", "鹿児島", "九州", "1860825"), //
    Okinawa("Okinawa-ken", "沖縄", "九州", "1854345"),;//

    private final String enName;
    private final String jpName;
    private final String region;
    @Getter
    private final String id;

    /** English name key map. */
    private static Map<String, OpenWeatherMapDTO> resoourceDtoMap = new HashMap<>();

    /** initial map. */
    static {
        for (REGION region : REGION.values()) {
            resoourceDtoMap.put(region.id, null);
        }
    }

    private REGION(String enName, String jpName, String region, String id) {
        this.enName = enName;
        this.jpName = jpName;
        this.region = region;
        this.id = id;
    }

    /**
     * Convert region Japanese name from English name
     *
     * @param name
     * @return
     */
    public static String toJpName(String name) {

        for (REGION region : REGION.values()) {
            if (region.enName.equals(name)) {
                return region.jpName;
            }
        }
        // not detect jpName
        throw new IllegalArgumentException("入力値が不正です [" + name + "]");
    }

    /**
     * <p>
     * Create Id list by enum values. Return list doesNOT sorted.
     *
     * @return idList
     */
    public static List<String> getIdList() {
        return Arrays.stream(REGION.values()).map(REGION::getId).collect(Collectors.toList());
    }

    /**
     * <p>
     * Store dto map by input map.
     *
     * @param idDtoMap {@code if inputMap.size()} does NOT 47 then
     *                 {@code throw IllegalArgumentException}
     * @return resoourceDtoMap has restored
     */
    public static Map<String, OpenWeatherMapDTO> store(Map<String, OpenWeatherMapDTO> idDtoMap) {
        if (idDtoMap.size() != 47) {
            throw new IllegalArgumentException("Input Map invalid size:" + idDtoMap.size());
        }

        // Restore Map by input
        for (Entry<String, OpenWeatherMapDTO> input : idDtoMap.entrySet()) {
            resoourceDtoMap.put(input.getKey(), input.getValue());
        }
        return resoourceDtoMap;
    }

    public static String getWeather(String cityName) {
        return resoourceDtoMap.get(cityName).getWeather().get(0).getMain();
    }

    public static int getHumid(String cityName) {
        return resoourceDtoMap.get(cityName).getMain().getHumidity();
    }

    public static double getTempMax(String cityName) {
        return resoourceDtoMap.get(cityName).getMain().getTemp_max();
    }
    public static double getTempMin(String cityName) {
        return resoourceDtoMap.get(cityName).getMain().getTemp_min();
    }

    public static double getWindSpeed(String cityName) {
        return resoourceDtoMap.get(cityName).getWind().getSpeed();
    }

    public static String getCityName(String cityName) {
        for (REGION region : REGION.values()) {
            if(region.enName.contentEquals(cityName)) {
                return region.jpName;
            };
        }
        throw new IllegalArgumentException("Input city name is invalid.Cause Not defined:" + cityName);
    }
}
