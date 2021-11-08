/*
 * Copyright (C) 2020 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
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
package device;

import java.util.List;

/**
 * This class is part of the device.Phone class, as it functions as the SMS
 * manager of the device. It contains three lists with SmsMessage objects. These
 * lists represent the inbox, drafts folder, and the outbox of the SMS manager
 * of the phone.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SmsManager {

    /**
     * The list of SMS messages for the inbox
     */
    private List<SmsMessage> inbox;

    /**
     * The list of SMS messages for the drafts folder
     */
    private List<SmsMessage> drafts;

    /**
     * The list of SMS messages for the outbox
     */
    private List<SmsMessage> outbox;

    /**
     * This class is part of the device.Phone class, as it functions as the SMS
     * manager of the device. It contains three lists with SmsMessage objects.
     * These lists represent the inbox, drafts folder, and the outbox of the SMS
     * manager of the phone.
     *
     * @param inbox the list of SMS messages for the inbox
     * @param drafts the list of SMS messages for the drafts folder
     * @param outbox the list of SMS messages for the outbox
     */
    public SmsManager(List<SmsMessage> inbox, List<SmsMessage> drafts, List<SmsMessage> outbox) {
        this.inbox = inbox;
        this.drafts = drafts;
        this.outbox = outbox;
    }

    /**
     * The list of SMS messages for the inbox
     *
     * @return the inbox
     */
    public List<SmsMessage> getInbox() {
        return inbox;
    }

    /**
     * The list of SMS messages for the drafts folder
     *
     * @return the drafts
     */
    public List<SmsMessage> getDrafts() {
        return drafts;
    }

    /**
     * The list of SMS messages for the outbox
     *
     * @return the outbox
     */
    public List<SmsMessage> getOutbox() {
        return outbox;
    }
}
