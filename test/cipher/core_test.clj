(ns cipher.core-test
  (:require [cipher.core :as core]     
            [midje.sweet :refer :all]))

(fact "this will fail"
  2 => 2)

(fact "sum two numbers"
 (tabular
   (core/sum ?a ?b) => ?result
   ?a ?b ?result
   3  3  6
   4  4  8
   37 20 57))
  
   

(fact "Transform char to number"
  (tabular
   (core/extract ?a) => ?result
    ?a ?result
    \z 25
    \e 4))
  

(fact "Transform number to char"
  (tabular
   (core/transform ?a) => ?result
    ?a ?result
    25 \z
    4  \e))
  
(fact "Transform letter" 
  (tabular
    (core/conversion ?letter ?key) => ?result
   ?letter ?key ?result
    \a      3    \d
    \b      20   \v
    \a      22   \w
    \d      -3   \a
    \a      100  \w))

(fact "Encode text"
 (->> "gato"
      (mapv #(core/conversion % 3))
      (apply str))=> "jdwr"
  (core/encrypt "casa" 3 ) => "fdvd") 


(fact "decrypt text"
  (core/decrypt "fdvd" 3) => "casa")


(fact "get letters from phrase"
  (core/get-letters "meu nome é Anne") => "meunomeeanne")

  
(fact "encrypt text"  
 (core/encrypt-text "Anne !" 5) => "fssj"
 (cdecrypt (core/encrypt-text "Anne !" 5) 5 ) => "anne")

   
  
 


  
  
  
