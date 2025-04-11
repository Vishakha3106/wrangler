/*
 * Copyright © 2025 Vishakha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Token implementation to parse and convert time duration strings like "10s", "5min", "3h", etc.
 */
public class TimeDuration implements Token {
    private final double value;
    private final String unit;

    public TimeDuration(String input) {
        input = input.trim().toLowerCase();
        int i = 0;
        while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
            i++;
        }
        this.value = Double.parseDouble(input.substring(0, i));
        this.unit = input.substring(i); // ms, s, min, h
    }

    public long getMilliseconds() {
        switch (unit) {
            case "ms":
                return (long) value;
            case "s":
                return (long) (value * 1000);
            case "min":
                return (long) (value * 1000 * 60);
            case "h":
                return (long) (value * 1000 * 60 * 60);
            default:
                return (long) value;
        }
    }

    @Override
    public Object value() {
        return getMilliseconds();
    }

    @Override
    public TokenType type() {
        return TokenType.TIME_DURATION;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(getMilliseconds());
    }
}
