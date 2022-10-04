module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml, javafx.base;

    //ขอขอบคุณพี่ Google และโดยเฉพาะอย่างยิ่งบุคลาการจากกลุ่มอื่น ที่ทำให้ผมรู้ว่าผมโง่มากที่ลืมใส่บรรนทัดนี้ไปแล้วนั้ง Debug อยู่ต่างนานเป็นชม.
    //ทั้งๆที่คำตอบก็ print เป็น error ออกมาตรงหน้าผมหลายตั้งครั้งแล้ว
    //ดังสำนวนที่ว่า "เส้นผมบังภูเขา"
    opens ku.cs.models to javafx.base;
    opens ku.cs.services to javafx.base;
    //ทางผมและผมทื่ขอบตาดำเป็นหมีแพนดาแล้ว ขอให้เหตุการดังกล่าวไม่เกิดกับผมและพ้องเพื่อนของผมอีก เพราะผมเจอเหตุการดั้งกล่าวมา2รอบแล้ว (สาธุ) 555
}