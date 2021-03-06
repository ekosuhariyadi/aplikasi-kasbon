<?php
/*
 * Copyright (C) 2011 ArtiVisi Intermedia <info@artivisi.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Translate string helper
 * @author Anton Raharja
 */

/**
 * Translate string
 * @param string $str String to be translated
 * @param string $lang_file Language file, with or without extension
 * @return string Translated string
 */
function oborci_translate($str, $lang_file=NULL) {
        global $application_folder;
        if (isset($lang_file)) {
                $lang_file = str_replace('..', '.', $lang_file);
                $lang_file = str_replace('|', '', $lang_file);
                $lang_file = str_replace('`', '', $lang_file);
                $lang_file = str_replace('\'', '', $lang_file);
                $lang_file = str_replace('"', '', $lang_file);
                $lang_file = $application_folder.'/language/'.$lang_file.'_lang.php';
                if (file_exists($lang_file)) {
                        // include a language file $lang_file
                        // the language file contains $lang array of text and its translation
                        // the language file used is the same language file supported by CI
                        @include_once($lang_file);
                }
        } else {
                $lang_file = $application_folder.'/language/oborci_lang.php';
                if (file_exists($lang_file)) {
                        @include_once($lang_file);
                }
        }
        $returns = isset($lang[$str]) ? $lang[$str] : $str;
	return $returns;
}

/**
 * Shortify translate() to t()
 * @param string $str String to be translated
 * @param string $lang_file Language file, with or without extension
 * @return string Translated string
 */
function t($str, $lang_file=NULL) {
        return oborci_translate($str, $lang_file);
}

?>
