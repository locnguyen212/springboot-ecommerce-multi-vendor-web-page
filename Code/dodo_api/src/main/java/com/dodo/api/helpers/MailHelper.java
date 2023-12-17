package com.dodo.api.helpers;

public class MailHelper {
	public static String getEmailShopApproveNoti(String name) {
		var strVar="";
		strVar += "<html>";
		strVar += "  <head>  ";
		strVar += "  </head>";
		strVar += "  <body>";
		strVar += "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>";
		strVar += "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">";
		strVar += "      <tr>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "        <td class=\"container\">";
		strVar += "          <div class=\"content\">";
		strVar += "";
		strVar += "            <table role=\"presentation\" class=\"main\">";
		strVar += "";
		strVar += "              <tr>";
		strVar += "                <td class=\"wrapper\">";
		strVar += "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                    <tr>";
		strVar += "                      <td>";
		strVar += "                        <p>Hi there,</p>";
		strVar += "                        <p>Here is your shop approval notification mail</p>";
		strVar += "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">";
		strVar += "                          <tbody>";
		strVar += "                            <tr>";
		strVar += "                              <td align=\"left\">";
		strVar += "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                                  <tbody>";
		strVar += "                                    <tr>";
		strVar += "                                      <td> <div>Your shop has been approved</div> </td>";
		strVar += "                                    </tr>";
		strVar += "                                  </tbody>";
		strVar += "                                </table>";
		strVar += "                              </td>";
		strVar += "                            </tr>";
		strVar += "                          </tbody>";
		strVar += "                        </table>";
		strVar += "                        <p>Good luck!.</p>";
		strVar += "                      </td>";
		strVar += "                    </tr>";
		strVar += "                  </table>";
		strVar += "                </td>";
		strVar += "              </tr>";
		strVar += "";
		strVar += "            </table>      ";
		strVar += "          </div>";
		strVar += "        </td>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "      </tr>";
		strVar += "    </table>";
		strVar += "  </body>";
		strVar += "</html>";
		return strVar;
	}
	
	public static String getEmailShopRefuseNoti(String name) {
		var strVar="";
		strVar += "<html>";
		strVar += "  <head>  ";
		strVar += "  </head>";
		strVar += "  <body>";
		strVar += "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>";
		strVar += "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">";
		strVar += "      <tr>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "        <td class=\"container\">";
		strVar += "          <div class=\"content\">";
		strVar += "";
		strVar += "            <table role=\"presentation\" class=\"main\">";
		strVar += "";
		strVar += "              <tr>";
		strVar += "                <td class=\"wrapper\">";
		strVar += "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                    <tr>";
		strVar += "                      <td>";
		strVar += "                        <p>Hi there,</p>";
		strVar += "                        <p>Here is your shop refuse notification mail</p>";
		strVar += "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">";
		strVar += "                          <tbody>";
		strVar += "                            <tr>";
		strVar += "                              <td align=\"left\">";
		strVar += "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                                  <tbody>";
		strVar += "                                    <tr>";
		strVar += "                                      <td> <div>Your shop create request has been refused</div> </td>";
		strVar += "                                    </tr>";
		strVar += "                                  </tbody>";
		strVar += "                                </table>";
		strVar += "                              </td>";
		strVar += "                            </tr>";
		strVar += "                          </tbody>";
		strVar += "                        </table>";
		strVar += "                        <p>Good luck!.</p>";
		strVar += "                      </td>";
		strVar += "                    </tr>";
		strVar += "                  </table>";
		strVar += "                </td>";
		strVar += "              </tr>";
		strVar += "";
		strVar += "            </table>      ";
		strVar += "          </div>";
		strVar += "        </td>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "      </tr>";
		strVar += "    </table>";
		strVar += "  </body>";
		strVar += "</html>";
		return strVar;
	}
	
	public static String getEmailShopDeactiveNoti(String name) {
		var strVar="";
		strVar += "<html>";
		strVar += "  <head>  ";
		strVar += "  </head>";
		strVar += "  <body>";
		strVar += "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>";
		strVar += "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">";
		strVar += "      <tr>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "        <td class=\"container\">";
		strVar += "          <div class=\"content\">";
		strVar += "";
		strVar += "            <table role=\"presentation\" class=\"main\">";
		strVar += "";
		strVar += "              <tr>";
		strVar += "                <td class=\"wrapper\">";
		strVar += "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                    <tr>";
		strVar += "                      <td>";
		strVar += "                        <p>Hi there,</p>";
		strVar += "                        <p>Here is your shop deactive notification mail</p>";
		strVar += "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">";
		strVar += "                          <tbody>";
		strVar += "                            <tr>";
		strVar += "                              <td align=\"left\">";
		strVar += "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                                  <tbody>";
		strVar += "                                    <tr>";
		strVar += "                                      <td> <div>Your shop has been deactived</div> </td>";
		strVar += "                                    </tr>";
		strVar += "                                  </tbody>";
		strVar += "                                </table>";
		strVar += "                              </td>";
		strVar += "                            </tr>";
		strVar += "                          </tbody>";
		strVar += "                        </table>";
		strVar += "                        <p>Good luck!.</p>";
		strVar += "                      </td>";
		strVar += "                    </tr>";
		strVar += "                  </table>";
		strVar += "                </td>";
		strVar += "              </tr>";
		strVar += "";
		strVar += "            </table>      ";
		strVar += "          </div>";
		strVar += "        </td>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "      </tr>";
		strVar += "    </table>";
		strVar += "  </body>";
		strVar += "</html>";
		return strVar;
	}
	
	public static String getEmailShopActiveNoti(String name) {
		var strVar="";
		strVar += "<html>";
		strVar += "  <head>  ";
		strVar += "  </head>";
		strVar += "  <body>";
		strVar += "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>";
		strVar += "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">";
		strVar += "      <tr>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "        <td class=\"container\">";
		strVar += "          <div class=\"content\">";
		strVar += "";
		strVar += "            <table role=\"presentation\" class=\"main\">";
		strVar += "";
		strVar += "              <tr>";
		strVar += "                <td class=\"wrapper\">";
		strVar += "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                    <tr>";
		strVar += "                      <td>";
		strVar += "                        <p>Hi there,</p>";
		strVar += "                        <p>Here is your shop active notification mail</p>";
		strVar += "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">";
		strVar += "                          <tbody>";
		strVar += "                            <tr>";
		strVar += "                              <td align=\"left\">";
		strVar += "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                                  <tbody>";
		strVar += "                                    <tr>";
		strVar += "                                      <td> <div>Congratulation, your shop has been active again</div> </td>";
		strVar += "                                    </tr>";
		strVar += "                                  </tbody>";
		strVar += "                                </table>";
		strVar += "                              </td>";
		strVar += "                            </tr>";
		strVar += "                          </tbody>";
		strVar += "                        </table>";
		strVar += "                        <p>Good luck!.</p>";
		strVar += "                      </td>";
		strVar += "                    </tr>";
		strVar += "                  </table>";
		strVar += "                </td>";
		strVar += "              </tr>";
		strVar += "";
		strVar += "            </table>      ";
		strVar += "          </div>";
		strVar += "        </td>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "      </tr>";
		strVar += "    </table>";
		strVar += "  </body>";
		strVar += "</html>";
		return strVar;
	}
	
	public static String getEmailActiveAccount(int id) {
		var strVar="";
		strVar += "<html>";
		strVar += "  <head>  ";
		strVar += "  </head>";
		strVar += "  <body>";
		strVar += "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>";
		strVar += "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">";
		strVar += "      <tr>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "        <td class=\"container\">";
		strVar += "          <div class=\"content\">";
		strVar += "";
		strVar += "            <table role=\"presentation\" class=\"main\">";
		strVar += "";
		strVar += "              <tr>";
		strVar += "                <td class=\"wrapper\">";
		strVar += "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                    <tr>";
		strVar += "                      <td>";
		strVar += "                        <p>Hi there,</p>";
		strVar += "                        <p>Here is your account active mail</p>";
		strVar += "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">";
		strVar += "                          <tbody>";
		strVar += "                            <tr>";
		strVar += "                              <td align=\"left\">";
		strVar += "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                                  <tbody>";
		strVar += "                                    <tr>";
		strVar += "                                      <td> <a href=\"http://localhost:9899/account/active/activate/"+id+"\" target=\"_blank\">Active account</a> </td>";
		strVar += "                                    </tr>";
		strVar += "                                  </tbody>";
		strVar += "                                </table>";
		strVar += "                              </td>";
		strVar += "                            </tr>";
		strVar += "                          </tbody>";
		strVar += "                        </table>";
		strVar += "                        <p>Click active account to continue.</p>";
		strVar += "                        <p>Good luck!.</p>";
		strVar += "                      </td>";
		strVar += "                    </tr>";
		strVar += "                  </table>";
		strVar += "                </td>";
		strVar += "              </tr>";
		strVar += "";
		strVar += "            </table>      ";
		strVar += "          </div>";
		strVar += "        </td>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "      </tr>";
		strVar += "    </table>";
		strVar += "  </body>";
		strVar += "</html>";
		return strVar;
	}
	
	public static String getMailResetPassword(int id, String token) {
		var strVar="";
		strVar += "<html>";
		strVar += "  <head>  ";
		strVar += "  </head>";
		strVar += "  <body>";
		strVar += "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>";
		strVar += "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">";
		strVar += "      <tr>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "        <td class=\"container\">";
		strVar += "          <div class=\"content\">";
		strVar += "";
		strVar += "            <table role=\"presentation\" class=\"main\">";
		strVar += "";
		strVar += "              <tr>";
		strVar += "                <td class=\"wrapper\">";
		strVar += "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                    <tr>";
		strVar += "                      <td>";
		strVar += "                        <p>Hi there,</p>";
		strVar += "                        <p>Here is your password reset mail</p>";
		strVar += "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">";
		strVar += "                          <tbody>";
		strVar += "                            <tr>";
		strVar += "                              <td align=\"left\">";
		strVar += "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		strVar += "                                  <tbody>";
		strVar += "                                    <tr>";
		strVar += "                                      <td> <a href=\"http://localhost:9899/account/forget-password/reset/"+id+"/"+token+"\" target=\"_blank\">Reset password</a> </td>";
		strVar += "                                    </tr>";
		strVar += "                                  </tbody>";
		strVar += "                                </table>";
		strVar += "                              </td>";
		strVar += "                            </tr>";
		strVar += "                          </tbody>";
		strVar += "                        </table>";
		strVar += "                        <p>Click reset password to continue.</p>";
		strVar += "                        <p>Good luck!.</p>";
		strVar += "                      </td>";
		strVar += "                    </tr>";
		strVar += "                  </table>";
		strVar += "                </td>";
		strVar += "              </tr>";
		strVar += "";
		strVar += "            </table>      ";
		strVar += "          </div>";
		strVar += "        </td>";
		strVar += "        <td>&nbsp;</td>";
		strVar += "      </tr>";
		strVar += "    </table>";
		strVar += "  </body>";
		strVar += "</html>";
		return strVar;
	}
}
