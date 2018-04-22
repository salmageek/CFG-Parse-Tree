package Rules;


public class Type_R implements Node {
   DataType_R dateType;
   Array_R array_;

    public Type_R(DataType_R dateType, Array_R array_) {
        this.dateType = dateType;
        this.array_ = array_;
    }

    @Override
    public void printNode() {
        dateType.printNode();
        if (array_ != null)
            array_.printNode();
    }
}
