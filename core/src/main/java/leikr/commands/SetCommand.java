/*
 * Copyright 2020 tor.
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
package leikr.commands;

import java.util.ArrayList;
import java.util.List;
import leikr.customProperties.CustomProgramProperties;

/**
 *
 * @author tor
 */
public class SetCommand extends Command {

    public SetCommand() {
        this.name = "set";
        properties = new ArrayList<>();
        properties.add("author");
        properties.add("use_compiled");
        properties.add("about");
        properties.add("ver");
        properties.add("version");
        properties.add("compile_source");
    }

    private final List<String> properties;

    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            return "[E] Not enough arguments.";
        }
        if (!properties.contains(args[3])) {
            return "[W] Property [" + args[2] + "] not found in Program [" + args[1] + "]";
        }
        CustomProgramProperties props = new CustomProgramProperties("Programs/" + args[1]);
        switch (args[2].toLowerCase()) {
            case "author" ->
                props.AUTHOR = args[3];
            case "use_compiled" ->
                props.USE_COMPILED = Boolean.valueOf(args[3]);
            case "about" ->
                props.ABOUT = args[3];
            case "ver" ->
                props.VERSION = args[3];
            case "version" ->
                props.VERSION = args[3];
            case "compile_source" ->
                props.COMPILE_SOURCE = Boolean.valueOf(args[3]);
        }
        props.writeProperties("Programs/" + args[1]);
        return props.toString();
    }

    @Override
    public String help() {
        return ">set [Program] [Property] [Value] \nSets the given program's property to the given value.";
    }

}
