import { WebPlugin } from '@capacitor/core';

import type { CapacitorMuxPlugin } from './definitions';

export class CapacitorMuxWeb extends WebPlugin implements CapacitorMuxPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
