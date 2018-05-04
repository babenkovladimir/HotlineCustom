package com.example.vladimirbabenko.hotlinecustom.data;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class RealmMigrations implements RealmMigration {

    @Override public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
      final RealmSchema schema = realm.getSchema();

      //migration mechanism
      //LastBookingEntity extends RealmObject
      //oldVersion -> look in DbHelper constructor and newVersion-1
      if (oldVersion == 1) {
        //schema.create("PartnerEntity");
        //final RealmObjectSchema userSchema = schema.create("PartnerEntity");
        //userSchema.addField("id", Integer.class);
        //userSchema.addField("title", String.class);
        //userSchema.addField("partnersPage", String.class);
        //userSchema.addField("image", String.class);

        ////schema.create("GoodsCategoryEntity");
        //final RealmObjectSchema userSchema1 = schema.create("GoodsCategoryEntity");
        //userSchema1.addField("id", Integer.class);
        //userSchema1.addField("title", String.class);
        //userSchema1.addField("text", String.class);
        //userSchema1.addRealmListField("children", schema.get("GoodsSubCategoryEntity"));
      }
    }
  }
