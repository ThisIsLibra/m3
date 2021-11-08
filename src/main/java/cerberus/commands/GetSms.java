/*
 * Copyright (C) 2021 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cerberus.commands;

import cerberus.bot.CerberusBot;
import device.SmsMessage;
import java.util.List;

/**
 * This command prepares all SMS messages on the phone for the upcoming upload
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetSms {

    /**
     * This command prepares all SMS messages on the phone for the upcoming
     * upload
     *
     * @param bot the bot
     */
    public GetSms(CerberusBot bot) {
        String logs = "";
        List<SmsMessage> sent = bot.getPhone().getSmsManager().getOutbox();
        List<SmsMessage> inbox = bot.getPhone().getSmsManager().getInbox();
        List<SmsMessage> drafts = bot.getPhone().getSmsManager().getDrafts();

        for (SmsMessage smsMessage : sent) {
            logs = logs + "~" + "sms/sent" + "~" + "number: " + smsMessage.getRecipient() + " text: " + smsMessage.getText() + ":end:";
        }

        for (SmsMessage smsMessage : inbox) {
            logs = logs + "~" + "sms/inbox" + "~" + "number: " + smsMessage.getRecipient() + " text: " + smsMessage.getText() + ":end:";
        }

        for (SmsMessage smsMessage : drafts) {
            logs = logs + "~" + "sms/draft" + "~" + "number: " + smsMessage.getRecipient() + " text: " + smsMessage.getText() + ":end:";
        }

        bot.getPhone().getSharedPreferences().write("logsSavedSMS", logs);

        bot.addLog("Received the command to get all text messages from the inbox, draft, and sent folder. The output is written to the shared preference named \"logsSavedSMS\", from where they will be uploaded later on");
    }
}
