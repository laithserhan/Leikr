/*
 * Copyright 2019 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package leikr.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.DesktopMini2DxGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Mini2DxConfig;
import java.io.File;
import java.security.Security;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import leikr.GameRuntime;

public class DesktopLauncher {

    public static void main(String[] arg) {

        if (Arrays.asList(arg).contains("insecure")) {
            Logger.getLogger(Security.class.getName()).log(Level.WARNING, "Leikr is running without security policy.");
        } else {
            Logger.getLogger(Security.class.getName()).log(Level.INFO, "Setting Leikr security policy.");
            System.setProperty("java.security.policy", new File("Sys/mysecurity.policy").getAbsolutePath());
            System.setSecurityManager(new SecurityManager());
        }
        Lwjgl3Mini2DxConfig config = new Lwjgl3Mini2DxConfig(GameRuntime.GAME_IDENTIFIER);
        // Required to temp fix input lag on Linux
        config.targetFPS = 60;
        config.setTitle("Leikr");
        config.setWindowedMode(720, 480);
        config.useVsync(true);
        config.setWindowIcon(Files.FileType.Internal, "Data/Logo/logo-16x16.png", "Data/Logo/logo-32x32.png");
        
        new DesktopMini2DxGame(new GameRuntime(arg), config);
    }
}
