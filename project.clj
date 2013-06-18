(defproject clojure-workflow-demo "0.1.0-SNAPSHOT"
  :description "Clojure Workflow Demo"
  :url "https://github.com/simon-katz/clojure-workflow-demo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring "1.1.8"]
                 [midje "1.5.1"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler clojure-workflow-demo.system/the-production-handler}
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.3"]
                                  [com.cemerick/pomegranate "0.2.0"]
                                  ;; [slamhound "1.3.3"]
                                  [spyscope "0.1.3"]
                                  [criterium "0.4.1"]
                                  [org.clojure/java.classpath "0.2.0"]]}})
