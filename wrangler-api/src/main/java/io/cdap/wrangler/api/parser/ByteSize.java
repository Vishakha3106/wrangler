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
 * Token implementation to parse and convert byte size strings like "10MB", "5KB", etc.
 */
public class ByteSize implements Token {
    private final double value;
    private final String unit;

    public ByteSize(String input) {
        input = input.trim().toUpperCase();
        int i = 0;
        while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
            i++;
        }
        this.value = Double.parseDouble(input.substring(0, i));
        this.unit = input.substring(i);
    }

    public long getBytes() {
        switch (unit) {
            case "KB":
                return (long) (value * 1024);
            case "MB":
                return (long) (value * 1024 * 1024);
            case "GB":
                return (long) (value * 1024 * 1024 * 1024);
            case "TB":
                return (long) (value * 1024L * 1024L * 1024L * 1024L);
            case "B":
            default:
                return (long) value;
        }
    }

    @Override
    public Object value() {
        return getBytes();
    }

    @Override
    public TokenType type() {
        return TokenType.BYTE_SIZE;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(getBytes());
    }
}
