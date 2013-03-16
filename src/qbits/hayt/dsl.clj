(ns qbits.hayt.dsl
  (:require
   [clojure.core.typed :as t]
   [qbits.hayt.types
    :refer [HaytQuery HaytClause CQLIdentifier CQLPermission]])
  (:import [clojure.lang APersistentMap]))

(t/ann select [CQLIdentifier HaytClause * -> HaytQuery])
(defn select
  "http://cassandra.apache.org/doc/cql3/CQL.html#selectStmt

Takes a table identifier and additional clause arguments:

* columns (defaults to *)
* where
* order-by
* limit
* table (optionaly using composition)"
  [table & clauses]
  (into {:select table :columns :*} clauses))

(t/ann insert [CQLIdentifier HaytClause * -> HaytQuery])
(defn insert
  "http://cassandra.apache.org/doc/cql3/CQL.html#insertStmt

Takes a table identifier and additional clause arguments:
* values
* using
* table (optionaly using composition)"
  [table & clauses]
  (into {:insert table} clauses))

(t/ann update [CQLIdentifier HaytClause * -> HaytQuery])
(defn update
  "http://cassandra.apache.org/doc/cql3/CQL.html#updateStmt

Takes a table identifier and additional clause arguments:

* using
* set-columns
* where
* table (optionaly using composition)"
  [table & clauses]
  (into {:update table} clauses))

(t/ann delete [CQLIdentifier HaytClause * -> HaytQuery])
(defn delete
  "http://cassandra.apache.org/doc/cql3/CQL.html#deleteStmt

Takes a table identifier and additional clause arguments:

* columns (defaults to *)
* using
* where
* table (optionaly using composition)"
  [table & clauses]
  (into {:delete table :columns :*} clauses))

(t/ann truncate [CQLIdentifier -> HaytQuery])
(defn truncate
  "http://cassandra.apache.org/doc/cql3/CQL.html#truncateStmt

Takes a table identifier."
  [table]
  {:truncate table})

(t/ann drop-keyspace [CQLIdentifier -> HaytQuery])
(defn drop-keyspace
  "http://cassandra.apache.org/doc/cql3/CQL.html#dropKeyspaceStmt

Takes a keyspace identifier"
  [keyspace]
  {:drop-keyspace keyspace})

(t/ann drop-table [CQLIdentifier -> HaytQuery])
(defn drop-table
  "http://cassandra.apache.org/doc/cql3/CQL.html#dropTableStmt

Takes a table identifier"
  [table]
  {:drop-table table})

(t/ann drop-index [CQLIdentifier -> HaytQuery])
(defn drop-index
  "http://cassandra.apache.org/doc/cql3/CQL.html#dropIndexStmt

Takes an index identifier."
  [index]
  {:drop-index index})

(t/ann create-index [CQLIdentifier CQLIdentifier HaytClause * -> HaytQuery])
(defn create-index
  "http://cassandra.apache.org/doc/cql3/CQL.html#createIndexStmt

Takes a table identifier and additional clause arguments:

* index-column
* index-name
* table (optionaly using composition)"
  [table name & clauses]
  (into {:create-index name :on table} clauses))

(t/ann create-keyspace [CQLIdentifier HaytClause * -> HaytQuery])
(defn create-keyspace
  "http://cassandra.apache.org/doc/cql3/CQL.html#createKeyspaceStmt

Takes a keyspace identifier and clauses:
* with
* keyspace (optionaly using composition)"
  [keyspace & clauses]
  (into {:create-keyspace keyspace} clauses))

(t/ann create-table [CQLIdentifier HaytClause * -> HaytQuery])
(defn create-table
  "Takes a table identifier and additional clause arguments:

* column-definitions
* with"
  [table & clauses]
  (into {:create-table table} clauses))

(t/ann alter-table [CQLIdentifier HaytClause * -> HaytQuery])
(defn alter-table
  "http://cassandra.apache.org/doc/cql3/CQL.html#alterTableStmt

Takes a table identifier and additional clause arguments:

* alter-column
* add
* with
* alter
* rename"
  [table & clauses]
  (into {:alter-table table} clauses))

(t/ann alter-column-family [CQLIdentifier HaytClause * -> HaytQuery])
(defn alter-column-family
  "http://cassandra.apache.org/doc/cql3/CQL.html#alterTableStmt

Takes a column-familiy identifier and additional clause arguments:

* alter-column
* add
* with
* alter
* rename
* column-family (optionaly using composition)"
  [column-family & clauses]
  (into {:alter-column-family column-family} clauses))

(t/ann truncate [CQLIdentifier HaytClause * -> HaytQuery])
(defn alter-keyspace
  "http://cassandra.apache.org/doc/cql3/CQL.html#alterKeyspaceStmt

Takes a keyspace identifier and a `with` clause.
* keyspace (optionaly using composition)"
  [keyspace & clauses]
  (into {:alter-keyspace keyspace} clauses))

(t/ann batch [HaytClause * -> HaytQuery])
(defn batch
  "http://cassandra.apache.org/doc/cql3/CQL.html#batchStmt

Takes hayt queries  optional clauses:
* using
* counter
* logged "
  [& clauses]
  (into {:logged true} clauses))

(t/ann use-keyspace [CQLIdentifier -> HaytQuery])
(defn use-keyspace
  "http://cassandra.apache.org/doc/cql3/CQL.html#useStmt

Takes a keyspace identifier"
  [keyspace]
  {:use-keyspace keyspace})

(t/ann grant [CQLIdentifier HaytClause * -> HaytQuery])
(defn grant
  "Takes clauses:
* resource
* user"
  [perm & clauses]
  (into {:grant perm} clauses))

(t/ann revoke [CQLIdentifier HaytClause * -> HaytQuery])
(defn revoke
  "Takes clauses:
* resource
* user"
  [perm & clauses]
  (into {:revoke perm} clauses))

(t/ann create-user [CQLIdentifier HaytClause * -> HaytQuery])
(defn create-user
  "Takes clauses:
* password
* superuser (defaults to false)"
  [user & clauses]
  (into {:create-user user :superuser false} clauses))

(t/ann alter-user [CQLIdentifier HaytClause * -> HaytQuery])
(defn alter-user
  "Takes clauses:
* password
* superuser (defaults to false)"
  [user & clauses]
  (into {:alter-user user :superuser false} clauses))

(t/ann drop-user [CQLIdentifier -> HaytQuery])
(defn drop-user
  [user]
  {:drop-user user})

(t/ann list-users [-> HaytQuery])
(defn list-users
  []
  {:list-users nil})

(t/ann list-perm [HaytClause * -> HaytQuery])
(defn list-perm
  "Takes clauses:
* perm (defaults to ALL if not supplied)
* user
* resource
* recursive (defaults to true)"
  [& clauses]
  (into {:list-perm :ALL :recursive true} clauses))

;; Clauses

(t/ann columns [CQLIdentifier * -> HaytClause])
(defn columns
  "Clause: takes columns identifiers"
  [& columns]
  {:columns columns})

;; TODO: create an alias for possible values
(t/ann column-definitions [Any -> HaytClause])
(defn column-definitions
  "Clause: "
  [column-definitions]
  {:column-definitions column-definitions})

;; TODO: create an alias for possible values
(t/ann using [Any * -> HaytClause])
(defn using
  "Clause: takes keyword/value pairs for :timestamp and :ttl"
  [& args]
  {:using (apply hash-map args)})

(t/ann limit [Number -> HaytClause])
(defn limit
  "Clause: takes a numeric value"
  [n]
  {:limit n})

(t/ann order-by ['[CQLIdentifier (U ':asc ':desc)] * -> HaytClause])
(defn order-by
  "Clause: takes vectors of 2 elements, where the first is the column
  identifier and the second is the ordering as keyword.
  ex: :asc, :desc"
  [& columns] {:order-by columns})

(t/ann queries [HaytQuery * -> HaytClause])
(defn queries
  "Clause: takes hayt queries to be executed during a batch operation."
  [& queries]
  {:batch queries})

;; TODO: create an alias for possible values
(t/ann column-definitions [(APersistentMap Any Any) -> HaytClause])
(defn where
  "Clause: takes a map or a vector of pairs to compose the where
clause of a select/update/delete query"
  [args]
  {:where args})

;; TODO: create an alias for possible values
(t/ann values [(APersistentMap Any Any) -> HaytClause])
(defn values
  "Clause: "
  [values]
  {:values values})

;; TODO: create an alias for possible values
(t/ann values [(APersistentMap Any Any) -> HaytClause])
(defn set-columns
  "Clause: "
  [values]
  {:set-columns values})

;; TODO: create an alias for possible values
(t/ann with [(APersistentMap Any Any) -> HaytClause])
(defn with
  "Clause: "
  [values]
  {:with values})

(t/ann index-name [CQLIdentifier -> HaytClause])
(defn index-name
  "Clause: "
  [value]
  {:index-name value})

(defn alter-column
  "Clause: "
  [& args]
  {:alter-column args})

(defn add-column
  "Clause: "
  [& args]
  {:add-column args})

(defn rename-column
  "Clause: "
  [& args]
  {:rename-column args})

(defn allow-filtering
  "Clause: "
  [value]
  {:allow-filtering value})

(t/ann logged [Boolean -> HaytClause])
(defn logged
  "Clause: "
  [value]
  {:logged value})

(t/ann counter [Boolean -> HaytClause])
(defn counter
  "Clause: "
  [value]
  {:counter value})

(t/ann superuser [Boolean -> HaytClause])
(defn superuser
  "Clause: "
  [value]
  {:superuser value})

(t/ann password [CQLIdentifier -> HaytClause])
(defn password
  "Clause: "
  [value]
  {:password value})

(t/ann recursive [Boolean -> HaytClause])
(defn recursive
  "Clause: "
  [value]
  {:recursive value})

(t/ann resource [CQLIdentifier -> HaytClause])
(defn resource
  "Clause: "
  [value]
  {:resource value})

(t/ann user [CQLIdentifier -> HaytClause])
(defn user
  "Clause: "
  [value]
  {:user value})

(t/ann perm [CQLPermission -> HaytClause])
(defn perm
  "Clause: "
  [value]
  {:list-perm value})
