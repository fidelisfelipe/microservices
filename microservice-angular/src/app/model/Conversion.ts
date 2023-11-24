import {TypeConversion} from "../types/type-conversion";

export class Conversion{
  constructor(
        public value: string,
        public from: TypeConversion,
        public to: TypeConversion,
        public totalCalculatedAmount: string
    ){}
}
