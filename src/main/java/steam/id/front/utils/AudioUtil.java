package steam.id.front.utils;

import com.vaadin.flow.component.UI;

public class AudioUtil {

    public static void play(Audio type) {
        String link = "";
        switch (type) {
            case ERROR -> link = "https://cdn.pixabay.com/download/" +
                    "audio/2021/08/09/audio_e00c3caecf.mp3?" +
                    "filename=bruh-sound-effect-1-6970.mp3";
            case SUCCESS -> link = "https://cdn.pixabay.com/download/" +
                    "audio/2021/08/09/audio_3728671ae0.mp3?" +
                    "filename=shouting-yeahwav-7043.mp3";
        }
        String audioCmd = "new Audio('{link}').play()";
        UI.getCurrent().getPage().executeJs(audioCmd.replace("{link}", link));
    }

    public enum Audio {
        ERROR,
        SUCCESS
    }
}
