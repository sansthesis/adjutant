(defproject adjutant "1.0.0-SNAPSHOT"
  :description "Adjutant is a tool that monitors servers and allows for actions to be taken upon them."
  :url "http://github.com/jasonrose/adjutant"
  :source-path "src/main/clojure"
  :compile-path "target/classes"
  :resources-path "src/main/resources"
  :test-path "src/test/clojure"
  :library-path "target/lib"
  :main adjutant.server
  :repl-init adjutant.server
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [noir "1.2.1"]])
