(set-env!
 :source-paths #{"src"}
 :dependencies '[[me.raynes/conch "0.8.0"]
                 [adzerk/boot-test "1.1.1" :scope "test"]])

(task-options!
 pom {:project 'my-project
      :version "0.1.0"}
 jar {:manifest {"Foo" "bar"}})

(require '[demo.boot-build :refer :all]
         '[adzerk.boot-test :refer :all])
