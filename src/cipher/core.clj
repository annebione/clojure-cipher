(ns cipher.core
  (:require [clojure.string :as str])
  (:import [java.text Normalizer])) 


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(defn sum [a, b]
  (+ a b))

(defn extract [letter]
  (let [ascii-a (int \a)]
    (- (int letter) ascii-a)))

(defn transform [number]
  (let [ascii-a (int \a)]
    (char (+ number ascii-a))))

(defn conversion [letter, key]
  (let [number-of-letters 26]
    (-> (extract letter)
        (+ key)
        (mod number-of-letters)
        (transform))))

(defn deaccent [str]
  (let [normalized (Normalizer/normalize str java.text.Normalizer$Form/NFD)]
    (clojure.string/replace normalized #"\p{InCombiningDiacriticalMarks}+" "")))


(defn encrypt [word key]
   (-> word
       (mapv #(conversion % key))
       (apply str)))


(defn decrypt [word key]
  (encrypt word (- key)))


(defn get-letters [phrase]
  (->>
       (str/lower-case phrase)
       (deaccent)
       (filterv #(Character/isLetter %))
       (apply str)))


(defn get-letters [phrase]
  (-> 
      (str/lower-case phrase)
      (deaccent)
      (filterv #(Character/isLetter %))
      (apply str)))



(defn encrypt-text [phrase key]
  (encrypt (get-letters phrase) key))
  
  
  


  
  
  
