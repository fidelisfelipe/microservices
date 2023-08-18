import {TypeConversion} from "../types/type-conversion";

export class Conversion{
  constructor(
        public id: number,
        public from: TypeConversion,
        public to: TypeConversion,
        public date: Date
    ){}
}
