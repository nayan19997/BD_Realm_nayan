package com.example.realm1;

import io.realm.RealmMigration;


import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();




        if(oldVersion == 0){
            Log.d("Migration", "actualizar versio 1");

            RealmObjectSchema objectSchema = schema.get("Persona");
            objectSchema
                    .addField("Correo", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("Correo",obj.getString("name") + obj.getString("edad") + "@Gmail.com");
                        }
                    })
                    .addIndex("Correo")
                  ;
            oldVersion++;
        }
    }
}

