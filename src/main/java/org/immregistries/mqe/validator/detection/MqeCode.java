package org.immregistries.mqe.validator.detection;

import java.util.HashMap;
import java.util.Map;

public enum MqeCode {
  MQE0000,
  MQE0002,
  MQE0003,
  MQE0004,
  MQE0005,
  MQE0006,
  MQE0014,
  MQE0015,
//  MQE0016, /* this was removed b/c the detection is no longer used. See Detection.java */
  MQE0017,
  MQE0018,
  MQE0020,
  MQE0023,
  MQE0030,
  MQE0032,
  MQE0035,
  MQE0037,
  MQE0038,
  MQE0039,
  MQE0056,
  MQE0057,
  MQE0058,
  MQE0059,
  MQE0060,
  MQE0061,
  MQE0062,
  MQE0063,
  MQE0064,
  MQE0065,
  MQE0066,
  MQE0067,
  MQE0068,
  MQE0069,
  MQE0070,
  MQE0071,
  MQE0072,
  MQE0073,
  MQE0074,
  MQE0075,
  MQE0076,
  MQE0077,
  MQE0078,
  MQE0079,
  MQE0080,
  MQE0081,
  MQE0082,
  MQE0083,
  MQE0084,
  MQE0085,
  MQE0086,
  MQE0087,
  MQE0088,
  MQE0089,
  MQE0090,
  MQE0091,
  MQE0092,
  MQE0093,
  MQE0094,
  MQE0095,
  MQE0096,
  MQE0097,
  MQE0098,
  MQE0099,
  MQE0100,
  MQE0101,
  MQE0102,
  MQE0103,
  MQE0104,
  MQE0105,
  MQE0106,
  MQE0107,
  MQE0108,
  MQE0109,
  MQE0110,
  MQE0111,
  MQE0112,
  MQE0113,
  MQE0114,
  MQE0115,
  MQE0116,
  MQE0117,
  MQE0118,
  MQE0119,
  MQE0120,
  MQE0121,
  MQE0122,
  MQE0123,
  MQE0124,
  MQE0125,
  MQE0126,
  MQE0127,
  MQE0128,
  MQE0129,
  MQE0130,
  MQE0131,
  MQE0132,
  MQE0133,
  MQE0134,
  MQE0135,
  MQE0136,
  MQE0137,
  MQE0138,
  MQE0139,
  MQE0140,
  MQE0141,
  MQE0142,
  MQE0143,
  MQE0144,
  MQE0145,
  MQE0146,
  MQE0147,
  MQE0148,
  MQE0149,
  MQE0150,
  MQE0151,
  MQE0152,
  MQE0153,
  MQE0154,
  MQE0155,
  MQE0156,
  MQE0157,
  MQE0158,
  MQE0159,
  MQE0160,
  MQE0161,
  MQE0162,
  MQE0163,
  MQE0164,
  MQE0165,
  MQE0166,
  MQE0167,
  MQE0168,
  MQE0170,
  MQE0171,
  MQE0172,
  MQE0173,
  MQE0174,
  MQE0175,
  MQE0176,
  MQE0177,
  MQE0178,
  MQE0179,
  MQE0180,
  MQE0181,
  MQE0182,
  MQE0183,
  MQE0184,
  MQE0185,
  MQE0186,
  MQE0187,
  MQE0188,
  MQE0189,
  MQE0190,
  MQE0191,
  MQE0192,
  MQE0193,
  MQE0194,
  MQE0195,
  MQE0196,
  MQE0197,
  MQE0198,
  MQE0199,
  MQE0200,
  MQE0201,
  MQE0202,
  MQE0203,
  MQE0204,
  MQE0205,
  MQE0206,
  MQE0207,
  MQE0208,
  MQE0209,
  MQE0210,
  MQE0211,
  MQE0212,
  MQE0213,
  MQE0214,
  MQE0215,
  MQE0216,
  MQE0217,
  MQE0218,
  MQE0219,
  MQE0220,
  MQE0221,
  MQE0222,
  MQE0223,
  MQE0224,
  MQE0225,
  MQE0226,
  MQE0227,
  MQE0228,
  MQE0229,
  MQE0230,
  MQE0231,
  MQE0232,
  MQE0233,
  MQE0234,
  MQE0235,
  MQE0236,
  MQE0237,
  MQE0238,
  MQE0239,
  MQE0240,
  MQE0241,
  MQE0242,
  MQE0243,
  MQE0244,
  MQE0245,
  MQE0246,
  MQE0247,
  MQE0248,
  MQE0249,
  MQE0250,
  MQE0251,
  MQE0252,
  MQE0253,
  MQE0254,
  MQE0255,
  MQE0256,
  MQE0257,
  MQE0258,
  MQE0259,
  MQE0260,
  MQE0261,
  MQE0262,
  MQE0263,
  MQE0264,
  MQE0265,
  MQE0266,
  MQE0267,
  MQE0268,
  MQE0269,
  MQE0270,
  MQE0271,
  MQE0272,
  MQE0273,
  MQE0274,
  MQE0275,
  MQE0276,
  MQE0277,
  MQE0278,
  MQE0279,
  MQE0280,
  MQE0281,
  MQE0282,
  MQE0283,
  MQE0284,
  MQE0285,
  MQE0286,
  MQE0287,
  MQE0288,
  MQE0289,
  MQE0290,
  MQE0291,
  MQE0292,
  MQE0293,
  MQE0294,
  MQE0295,
  MQE0296,
  MQE0297,
  MQE0298,
  MQE0299,
  MQE0300,
  MQE0301,
  MQE0302,
  MQE0303,
  MQE0304,
  MQE0305,
  MQE0306,
  MQE0307,
  MQE0308,
  MQE0309,
  MQE0310,
  MQE0311,
  MQE0312,
  MQE0313,
  MQE0314,
  MQE0315,
  MQE0316,
  MQE0317,
  MQE0318,
  MQE0319,
  MQE0320,
  MQE0321,
  MQE0322,
  MQE0323,
  MQE0324,
  MQE0325,
  MQE0326,
  MQE0327,
  MQE0328,
  MQE0329,
  MQE0330,
  MQE0331,
  MQE0332,
  MQE0333,
  MQE0334,
  MQE0335,
  MQE0336,
  MQE0337,
  MQE0338,
  MQE0339,
  MQE0340,
  MQE0341,
  MQE0342,
  MQE0343,
  MQE0344,
  MQE0345,
  MQE0346,
  MQE0347,
  MQE0348,
  MQE0349,
  MQE0350,
  MQE0351,
  MQE0352,
  MQE0353,
  MQE0354,
  MQE0355,
  MQE0356,
  MQE0357,
  MQE0358,
  MQE0359,
  MQE0360,
  MQE0361,
  MQE0362,
  MQE0363,
  MQE0364,
  MQE0369,
  MQE0370,
  MQE0371,
  MQE0372,
  MQE0373,
  MQE0374,
  MQE0375,
  MQE0376,
  MQE0377,
  MQE0378,
  MQE0379,
  MQE0380,
  MQE0381,
  MQE0382,
  MQE0383,
  MQE0384,
  MQE0385,
  MQE0386,
  MQE0387,
  MQE0388,
  MQE0393,
  MQE0394,
  MQE0395,
  MQE0396,
  MQE0397,
  MQE0398,
  MQE0399,
  MQE0405,
  MQE0406,
  MQE0407,
  MQE0408,
  MQE0409,
  MQE0410,
  MQE0439,
  MQE0441,
  MQE0442,
  MQE0443,
  MQE0444,
  MQE0445,
  MQE0446,
  MQE0447,
  MQE0448,
  MQE0449,
  MQE0450,
  MQE0451,
  MQE0453,
  MQE0454,
  MQE0455,
  MQE0456,
  MQE0457,
  MQE0458,
  MQE0459,
  MQE0460,
  MQE0461,
  MQE0462,
  MQE0465,
  MQE0466,
  MQE0467,
  MQE0468,
  MQE0469,
  MQE0470,
  MQE0471,
  MQE0472,
  MQE0473,
  MQE0474,
  MQE0475,
  MQE0476,
  MQE0477,
  MQE0478,
  MQE0479,
  MQE0480,
  MQE0481,
  MQE0482,
  MQE0483,
  MQE0484,
  MQE0485,
  MQE0486,
  MQE0487,
  MQE0488,
  MQE0489,
  MQE0490,
  MQE0491,
  MQE0492,
  MQE0493,
  MQE0494,
  MQE0495,
  MQE0496,
  MQE0497,
  MQE0498,
  MQE0499,
  MQE0500,
  MQE0501,
  MQE0502,
  MQE0503,
  MQE0504,
  MQE0505,
  MQE0506,
  MQE0507,
  MQE0508,
  MQE0509,
  MQE0510,
  MQE0511,
  MQE0512,
  MQE0513,
  MQE0514,
  MQE0515,
  MQE0516,
  MQE0517,
  MQE0518,
  MQE0519,
  MQE0520,
  MQE0521,
  MQE0522,
  MQE0523,
  MQE0526,
  MQE0527,
  MQE0528,
  MQE0529,
  MQE0530,
  MQE0531,
  MQE0532,
  MQE0533,
  MQE0534,
  MQE0535,
  MQE0536,
  MQE0537,
  MQE0538,
  MQE0539,
  MQE0540,
  MQE0541,
  MQE0542,
  MQE0543,
  MQE0544,
  MQE0545,
  MQE0547,
  MQE0548,
  MQE0549,
  MQE0550,
  MQE0551,
  MQE0552,
  MQE0553,
  MQE0554,
  MQE0555,
  MQE0556,
  MQE0557,
  MQE0558,
  MQE0559,
  MQE0560,
  MQE0561,
  MQE0562,
  MQE0563,
  MQE0564,
  MQE0565,
  MQE0566,
  MQE0567,
  MQE0568, 
  MQE0569,
  MQE0570,
  MQE0571,
  MQE0572,
  MQE0573,
  MQE0574,
  MQE0575,
  MQE0576,
  MQE0577,
  MQE0578,
  MQE0579,
  MQE0580,
  MQE0581,
  MQE0582,
  
  MQE0583,
  MQE0584,
  MQE0585,
  MQE0586,
  MQE0587,
  MQE0588,
  MQE0589,
  MQE0590,
  MQE0591,
  MQE0592,
  MQE0593,
  MQE0594,
  MQE0595,
  MQE0596,
  MQE0597,
  MQE0598,
  MQE0599,
  MQE0600,
  MQE0601,
  MQE0602,
  MQE0603,
  MQE0604,
  MQE0605,
  MQE0606,
  MQE0607,
  MQE0608,
  
  MQE0609,
  MQE0610,
  MQE0611,
  MQE0612,
  MQE0613,
  MQE0614,
  MQE0615,
  MQE0616,
  MQE0617,
  MQE0618,
  MQE0619,
  MQE0620,
  MQE0621,
  MQE0622,
  MQE0623,
  MQE0624,
  MQE0625,
  MQE0626,
  MQE0627,
  MQE0628,
  MQE0629,
  MQE0630,
  MQE0631,
  MQE0632,
  MQE0633,
  MQE0634,
  MQE0635,
  MQE0636,
  MQE0637,
  MQE0638,
  MQE0639,
  MQE0640,
  MQE0641,
  MQE0642,
  MQE0643,
  MQE0644,
  MQE0645,
  MQE0646,
  MQE0647,
  MQE0648,
  MQE0649,
  MQE0650,
  MQE0651,
  MQE0652,
  MQE0653,
  MQE0654,
  MQE0655,
  MQE0656,
  MQE0657,
  MQE0658,
  MQE0659,
  MQE0660,
  MQE0661,
  MQE0662,
  MQE0663,
  MQE0664,
  MQE0665,
  MQE0666,
  MQE0667,
  MQE0668,
  MQE0669,
  MQE0670,
  MQE0671,
  MQE0672,
  MQE0673,
  MQE0674,
  MQE0675,
  MQE0676,
  MQE0677,
  MQE0678,
  MQE0679,
  MQE0680,
  MQE0681,
  MQE0682,
  MQE0683,
  MQE0684,
  MQE0685,
  MQE0686,
  MQE0687,
  MQE0688,
  MQE0689,
  MQE0690,
  MQE0691,
  MQE0692,
  MQE0693,
  MQE0694,
  MQE0695,
  MQE0696,
  MQE0697,
  MQE0698,
  MQE0699,
  MQE0700,
  MQE0701,
  MQE0702,
  MQE0703,
  MQE0704,
  MQE0705,
  MQE0706,
  MQE0707,
  MQE0708,
  MQE0709,
  MQE0710,
  MQE0711,
  MQE0712,
  MQE0713,
  MQE0714,
  MQE0715,
  MQE0716,
  MQE0717,
  MQE0718,
  MQE0719,
  MQE0720,
  MQE0721,
  MQE0722,
  MQE0723,
  MQE0724,
  MQE0725,
  MQE0726,
  MQE0727,
  MQE0728,
  MQE0729,
  MQE0730,
  MQE0731,
  MQE0732,
  MQE0733,
  MQE0734,
  MQE0735,
  MQE0736,
  MQE0737,
  MQE0738,
  MQE0739,
  MQE0740,
  MQE0741,
  MQE0742,
  MQE0743,
  MQE0744,
  MQE0745,
  MQE0746,
  MQE0747,
  MQE0748,
  MQE0749,
  MQE0750,
  MQE0751,
  MQE0752,
  MQE0753,
  MQE0754,
  MQE0755,
  MQE0756,
  MQE0757,
  MQE0758,
  MQE0759,
  MQE0760,
  MQE0761,
  MQE0762,
  MQE0763,
  MQE0764,
  MQE0765,
  MQE0766,
  MQE0767,
  MQE0768,
  MQE0769,
  MQE0770,
  MQE0771,
  MQE0772,
  MQE0773,
  
  // Add new codes here below in ascending order
  ;

  private static Map<String, MqeCode> errorCodeStringMap = new HashMap<>();

  static {
    for (MqeCode c : MqeCode.values()) {
      errorCodeStringMap.put(c.name(), c);
    }
  }

  public static MqeCode getByCodeString(String code) {
    return errorCodeStringMap.get(code);
  }
}
